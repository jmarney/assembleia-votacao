package br.com.assembleiavota.service;

import br.com.assembleiavota.dto.SessaoAbrirDto;
import br.com.assembleiavota.dto.SessaoDto;
import br.com.assembleiavota.exception.NotFoundException;
import br.com.assembleiavota.model.Sessao;
import br.com.assembleiavota.repository.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoService.class);
    private static final Integer TEMPO_DEFAULT = 1;

    private final SessaoRepository repository;
    private final TopicoService topicoService;

    @Autowired
    public SessaoService(SessaoRepository repository, TopicoService topicoService) {
        this.repository = repository;
        this.topicoService = topicoService;
    }


    @Transactional
    public SessaoDto abrirSessao(SessaoAbrirDto sessaoAbrirDto) {
        LOGGER.debug("Abrindo sessao votacao para a pauta {}", sessaoAbrirDto.getIdTopico());
       isValidaAbrirSessao(sessaoAbrirDto);
            SessaoDto dto = new SessaoDto(
                    null,
                    LocalDateTime.now(),
                    calcularTempo(sessaoAbrirDto.getTempo()),
                    Boolean.TRUE);

            return salvar(dto);
    }

    @Transactional(readOnly = true)
    public boolean isValidaAbrirSessao(SessaoAbrirDto sessaoAbrirDto) {
        if (topicoService.isValidaPauta(sessaoAbrirDto.getIdTopico())) {
            return Boolean.TRUE;
        } else {
            throw new NotFoundException("Pauta não localizada id" + sessaoAbrirDto.getIdTopico());
        }
    }

    @Transactional(readOnly = true)
    public List<SessaoDto> buscarSessoesEmAndamento() {
        LOGGER.debug("Buscando sessoes em andamento");
        List<SessaoDto> list = repository.buscaSessoesAtivas(Boolean.TRUE)
                .stream()
                .map(SessaoDto::toDto)
                .collect(Collectors.toList());

        return list
                .stream()
                .filter(dto -> dto.getDataHoraFim().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void finalizarSessao(SessaoDto dto) {
        LOGGER.debug("Finalizar sessao com tempo de duracao expirado {}", dto.getId());
        dto.setAtiva(Boolean.FALSE);
        salvar(buscarSessaoById(dto.getId()));
    }

    @Transactional(readOnly = true)
    public SessaoDto buscarSessaoById(Integer id) {
        Optional<Sessao> optionalSessao = repository.findById(id);
        if (!optionalSessao.isPresent()) {
            LOGGER.error("Sessao de votacao nao localizada para o id {}", id);
            throw new NotFoundException("Sessão de votação não localizada para o id " + id);
        }
        return SessaoDto.toDto(optionalSessao.get());
    }

    @Transactional(readOnly = true)
    public Boolean isSessaoValida(Integer id) {
        return repository.existsSessaoAtivaByIdAndAtiva(id, Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    public Boolean isSessaoExiste(Integer id) {
        if (repository.existsSessaoById(id)) {
            return Boolean.TRUE;
        } else {
            LOGGER.error("Sessao de votacao nao localizada para o id {}", id);
            throw new NotFoundException("Sessão de votação não localizada para o id " + id);
        }
    }


    @Transactional(readOnly = true)
    public Boolean isSessaoValidaParaContagem(Integer id) {
        return repository.existsSessaoAtivaByIdAndAtiva(id, Boolean.FALSE);
    }


    private LocalDateTime calcularTempo(Integer tempo) {
        if (tempo != null && tempo != 0) {
            return LocalDateTime.now().plusMinutes(tempo);
        } else {
            return LocalDateTime.now().plusMinutes(TEMPO_DEFAULT);
        }
    }

    @Transactional
    public SessaoDto salvar(SessaoDto dto) {
        LOGGER.debug("Salvando a sessao");
        if (Optional.ofNullable(dto).isPresent()) {
            return SessaoDto.toDto(repository.save(SessaoDto.toEntity(dto)));
        }
        return null;
    }
}
