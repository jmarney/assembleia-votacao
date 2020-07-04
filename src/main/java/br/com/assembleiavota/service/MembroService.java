package br.com.assembleiavota.service;

import br.com.assembleiavota.cliente.ValidaClient;
import br.com.assembleiavota.dto.MembroDto;
import br.com.assembleiavota.repository.MembroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MembroService.class);
    private final MembroRepository repository;
    private ValidaClient validaClient;

    @Autowired
    public MembroService(MembroRepository repository, ValidaClient validaMembroCpf) {
        this.repository = repository;
        this.validaClient = validaMembroCpf;
    }

    @Transactional(readOnly = true)
    public Boolean isValidaParticipacaoAssociadoVotacao(String cpf, Integer idTopico) {
        LOGGER.debug("Valida o associado de cpf = {} na pauta  = {}", cpf, idTopico);
        return repository.validaByCpfAndIdTopico(cpf, idTopico);
    }

    @Transactional
    public void salvarMembro(MembroDto dto) {
        LOGGER.debug("Registra votacao do membro de cpf = {} na pauta = {}", dto.getCpf(), dto.getIdTopico());
        repository.save(MembroDto.toEntity(dto));
    }

    public boolean isValidoMembroVotar(String cpf) {
        return validaClient.validaClient(cpf);
    }
}
