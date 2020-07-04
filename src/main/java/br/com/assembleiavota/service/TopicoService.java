package br.com.assembleiavota.service;

import br.com.assembleiavota.dto.TopicoDto;
import br.com.assembleiavota.exception.NotFoundException;
import br.com.assembleiavota.model.Topico;
import br.com.assembleiavota.repository.TopicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicoService.class);

    @Autowired
    private TopicoRepository repository;


    @Transactional
    public TopicoDto salvar(TopicoDto dto) {
        return TopicoDto.toDto(repository.save(TopicoDto.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public List<TopicoDto> buscarPautas() {
        List<Topico> topicos = repository.findAll();

        if (topicos.isEmpty()) {
            LOGGER.error("Pautas não localizadas");
            throw new NotFoundException("Pautas não localizadas");
        }

        return TopicoDto.toDtos(topicos);
    }

    @Transactional(readOnly = true)
    public TopicoDto buscarPautaPeloId(Integer id) {
        Optional<Topico> topicoOptional = repository.findById(id);

        if (!topicoOptional.isPresent()) {
            LOGGER.error("Pauta não localizada para oid {}", id);
            throw new NotFoundException("Pauta não localizada referente a id " + id);
        }

        return TopicoDto.toDto(topicoOptional.get());
    }

    @Transactional(readOnly = true)
    public TopicoDto buscarPautaPelaDescricao(String descricao) {
        Optional<Topico> topicoOptional = repository.findByDescricao(descricao);

        if (!topicoOptional.isPresent()) {
            LOGGER.error("Pauta não localizada para oid {}", descricao);
            throw new NotFoundException("Pauta não localizada referente a descricao " + descricao);
        }

        return TopicoDto.toDto(topicoOptional.get());
    }

    @Transactional(readOnly = true)
    public Boolean isValidaPauta(Integer id) {
        if (repository.existsByid(id).isPresent()) {
            return Boolean.TRUE;
        } else {
            LOGGER.error("Pauta não localizada para id {}", id);
            throw new NotFoundException("Pauta não localizada para id " + id);
        }
    }
}
