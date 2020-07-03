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

import java.util.Optional;

@Service
public class TopicoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicoService.class);

    @Autowired
    private TopicoRepository repository;


    @Transactional
    public TopicoDto salvar(TopicoDto dto) {
        return TopicoDto.toDTO(repository.save(TopicoDto.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public TopicoDto buscarPautaPeloId(Integer id) {
        Optional<Topico> topicoOptional = repository.findById(id);

        if (!topicoOptional.isPresent()) {
            LOGGER.error("Pauta não localizada para oid {}", id);
            throw new NotFoundException("Pauta não localizada referente a id " + id);
        }

        return TopicoDto.toDTO(topicoOptional.get());
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
