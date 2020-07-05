package br.com.assembleiavota.service;

import br.com.assembleiavota.cliente.ValidaClient;
import br.com.assembleiavota.dto.MembroDto;
import br.com.assembleiavota.repository.MembroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MembroService.class);
    @Autowired
    private MembroRepository repository;
    @Autowired
    private ValidaClient validaClient;

    public Boolean existsByCpfAndIdTopico(String cpf, Integer idTopico) {
        LOGGER.debug("Valida o associado de cpf = {} na pauta  = {}", cpf, idTopico);
        return repository.existsByCpfAndIdTopico(cpf, idTopico);
    }

    public void salvarMembro(MembroDto dto) {
        LOGGER.debug("Registra votacao do membro de cpf = {} na pauta = {}", dto.getCpf(), dto.getIdTopico());
        repository.save(MembroDto.toEntity(dto));
    }

    public boolean isValidoMembroVotar(String cpf) {
        return validaClient.validaClient(cpf);
    }
}
