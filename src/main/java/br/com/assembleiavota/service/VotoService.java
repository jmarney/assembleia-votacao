package br.com.assembleiavota.service;

import br.com.assembleiavota.dto.*;
import br.com.assembleiavota.exception.BusinessException;
import br.com.assembleiavota.exception.NotFoundException;
import br.com.assembleiavota.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public boolean isValidaVoto(VotoDto dto) {
        LOGGER.debug("Validando os dados para voto idSessao = {}, idTopico = {}, cpf membro = {}", dto.getIdSessao(), dto.getIdTopico(), dto.getCpf());

        if (!topicoService.isValidaPauta(dto.getIdTopico())) {

            LOGGER.error("Pauta nao localizada para votacao oidPauta {}", dto.getCpf());
            throw new NotFoundException("Pauta não localizada id " + dto.getIdTopico());

        } else if (!sessaoService.isSessaoValida(dto.getIdSessao())) {

            LOGGER.error("Tentativa de voto para sessao encerrada idSessao {}", dto.getIdSessao());
            throw new NotFoundException("Sessão já encerrada");

        } else if (!membroService.isValidoMembroVotar(dto.getCpf())) {

            LOGGER.error("Associado nao esta habilitado para votar {}", dto.getCpf());
            throw new BusinessException("Membro não esta hablitado para votar nesta pauta");

        } else if (!membroService.isValidaParticipacaoAssociadoVotacao(dto.getCpf(), dto.getIdTopico())) {

            LOGGER.error("Associado tentou votar mais de 1 vez oidAssociado {}", dto.getCpf());
            throw new BusinessException("Membro ja votou nessa pauta");
        }

        return Boolean.TRUE;
    }

    @Transactional
    public String votar(VotoDto dto) {
        if (isValidaVoto(dto)) {
            LOGGER.debug("Dados validos para voto idSessao = {}, idPauta = {}, Cpf membro = {}", dto.getIdSessao(), dto.getIdTopico(), dto.getCpf());

            VotacaoDto votacaoDto = new VotacaoDto(null,
                    dto.getIdTopico(),
                    dto.getIdSessao(),
                    dto.getVoto(),
                    null,
                    null);

            registrarVoto(votacaoDto);

            registrarAssociadoVotou(dto);

            return "Voto validado";
        }
        return null;
    }

    @Transactional
    public void registrarAssociadoVotou(VotoDto dto) {
        MembroDto membroDto = new MembroDto(null, dto.getCpf(), dto.getIdTopico());
        membroService.salvarAssociado(membroDto);
    }

    @Transactional
    public void registrarVoto(VotacaoDto dto) {
        LOGGER.debug("Salvando o voto para pauta {}", dto.getIdTopico());
        repository.save(VotacaoDto.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public VotacaoDto buscarResultado(Integer idTopico, Integer idSessao) {
        LOGGER.debug("Contabilizando os votos para idTopico = {}, idSessao = {}", idTopico, idSessao);
        VotacaoDto dto = new VotacaoDto();

        dto.setIdTopico(idTopico);
        dto.setIdSessao(idSessao);

        dto.setQtdVotoSim(repository.qtdVotosByIdTopicoAndIdSessaoAndValido(idTopico, idSessao, Boolean.TRUE));
        dto.setQtdVotoNao(repository.qtdVotosByIdTopicoAndIdSessaoAndValido(idTopico, idSessao, Boolean.FALSE));

        return dto;
    }

    @Transactional(readOnly = true)
    public VotoResultadoDto buscarDadosResultadoVotacao(Integer idTopico, Integer oidSessaoVotacao) {

        if (isValidaDados(idTopico, oidSessaoVotacao) && sessaoService.isSessaoValidaParaContagem(oidSessaoVotacao)) {
            LOGGER.debug("Construindo o objeto de retorno do resultado para idTopico = {}, oidSessaoVotacao = {}", idTopico, oidSessaoVotacao);
            TopicoDto topicoDto = topicoService.buscarPautaPeloId(idTopico);
            VotacaoDto votacaoDTO = buscarResultado(idTopico, oidSessaoVotacao);
            return new VotoResultadoDto(topicoDto, votacaoDTO);
        }
        throw new NotFoundException("Sessão de votação ainda está aberta, não é possível obter a contagem do resultado.");
    }
   
    @Transactional(readOnly = true)
    public boolean isValidaDados(Integer idTopico, Integer idSessao) {
        return sessaoService.isSessaoExiste(idSessao) && topicoService.isValidaPauta(idTopico);
    }
}
