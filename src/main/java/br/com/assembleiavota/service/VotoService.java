package br.com.assembleiavota.service;

import br.com.assembleiavota.dto.*;
import br.com.assembleiavota.exception.BusinessException;
import br.com.assembleiavota.exception.NotFoundException;
import br.com.assembleiavota.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoService.class);

    @Autowired
    private VotoRepository repository;
    @Autowired
    private TopicoService topicoService;
    @Autowired
    private SessaoService sessaoService;
    @Autowired
    private MembroService membroService;


    public boolean isValidaVoto(VotoDto dto) {
        LOGGER.debug("Validando os dados para voto idSessao = {}, idTopico = {}, cpf membro = {}", dto.getIdSessao(), dto.getIdTopico(), dto.getCpf());

        if (!topicoService.isValidaPauta(dto.getIdTopico())) {

            LOGGER.error("Pauta nao localizada para votacao oidPauta {}", dto.getCpf());
            throw new NotFoundException("Pauta não localizada id " + dto.getIdTopico());

        } else if (!sessaoService.isSessaoValida(dto.getIdSessao())) {

            LOGGER.error("Tentativa de voto para sessao encerrada idSessao {}", dto.getIdSessao());
            throw new BusinessException("Sessão já encerrada");

        } else if (!membroService.isValidoMembroVotar(dto.getCpf())) {

            LOGGER.error("Associado nao esta habilitado para votar {}", dto.getCpf());
            throw new BusinessException("Membro não esta hablitado para votar nesta pauta");

        } else if (!membroService.isValidaParticipacaoAssociadoVotacao(dto.getCpf(), dto.getIdTopico())) {

            LOGGER.error("Associado tentou votar mais de 1 vez oidAssociado {}", dto.getCpf());
            throw new BusinessException("Membro ja votou nessa pauta");
        }

        return Boolean.TRUE;
    }


    public String votar(VotoDto dto) {
        if (isValidaVoto(dto)) {
            LOGGER.debug("Dados validos para voto idSessao = {}, IdTopico = {}, Cpf membro = {}", dto.getIdSessao(), dto.getIdTopico(), dto.getCpf());

            VotacaoDto votacaoDto = new VotacaoDto(null,
                    dto.getIdTopico(),
                    dto.getIdSessao(),
                    dto.getVoto(),
                    null,
                    null);

            registrarVoto(votacaoDto);

            registrarMembroVotou(dto);

            return "Voto validado";
        }
        return null;
    }


    public void registrarMembroVotou(VotoDto dto) {
        MembroDto membroDto = new MembroDto(null, dto.getCpf(), dto.getIdTopico());
        membroService.salvarMembro(membroDto);
    }


    public void registrarVoto(VotacaoDto dto) {
        LOGGER.debug("Computando voto na pauta {}", dto.getIdTopico());
        repository.save(VotacaoDto.toEntity(dto));
    }


    public VotacaoDto buscarResultado(Integer idTopico, Integer idSessao) {
        LOGGER.debug("Contabilizando votos para idTopico = {}, idSessao = {}", idTopico, idSessao);
        return new VotacaoDto().builder()
                .idTopico(idTopico)
                .idSessao(idSessao)
                .qtdVotoSim(repository.countVotosByIdTopicoAndIdSessaoAndVoto(idTopico, idSessao, Boolean.TRUE))
                .qtdVotoNao(repository.countVotosByIdTopicoAndIdSessaoAndVoto(idTopico, idSessao, Boolean.FALSE))
                .build();
    }


    public VotoResultadoDto buscarResultadoVotacao(Integer idTopico, Integer idSessao) {

        if (isValidaDados(idTopico, idSessao) && sessaoService.isSessaoValidaParaContagem(idSessao)) {
            LOGGER.debug("Construindo o objeto de retorno do resultado para idTopico = {}, idSessao = {}", idTopico, idSessao);
            TopicoDto topicoDto = topicoService.buscarPautaPeloId(idTopico);
            VotacaoDto votacaoDTO = buscarResultado(idTopico, idSessao);
            return new VotoResultadoDto(topicoDto, votacaoDTO);
        }
        throw new BusinessException("Sessão ainda aberta, não é possível obter o resultado ainda.");
    }

    public boolean isValidaDados(Integer idTopico, Integer idSessao) {
        return sessaoService.isSessaoExiste(idSessao) && topicoService.isValidaPauta(idTopico);
    }
}
