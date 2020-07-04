package br.com.assembleiavota.controller;

import br.com.assembleiavota.controller.swagger.VotoApi;
import br.com.assembleiavota.dto.VotoDto;
import br.com.assembleiavota.dto.VotoResultadoDto;
import br.com.assembleiavota.service.VotoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class VotoController implements VotoApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoController.class);

    @Autowired
    private VotoService service;

    @ApiOperation(value = "Votar uma pauta, caso esteja em andamento")
    @PostMapping(value = "/votar")
    public ResponseEntity<String> votar(@Valid @RequestBody VotoDto dto) {
        LOGGER.debug("Membro votando cpf = {}", dto.getCpf());
        String mensagem = service.votar(dto);
        LOGGER.debug("Voto computado cpf = {}", dto.getCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @ApiOperation(value = "Busca resultado da votacao")
    @GetMapping(value = "/resultado/{idTopico}/{idSessao}")
    public ResponseEntity<VotoResultadoDto> resultado(@PathVariable Integer idTopico, @PathVariable Integer idSessao) {
        LOGGER.debug("Buscando resultado da votacao idTopico = {} , idSessao = {} ", idTopico, idSessao);
        VotoResultadoDto votoResultadoDto = service.buscarResultadoVotacao(idTopico, idSessao);
        return ResponseEntity.status(HttpStatus.OK).body(votoResultadoDto);
    }

}
