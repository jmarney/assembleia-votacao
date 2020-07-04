package br.com.assembleiavota.controller;

import br.com.assembleiavota.dto.VotoDto;
import br.com.assembleiavota.dto.VotoResultadoDto;
import br.com.assembleiavota.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/votos")
@Api(value = "Votos", tags = "votos")
public class VotoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoController.class);

    private final VotoService service;

    @Autowired
    public VotoController(VotoService service) {
        this.service = service;
    }

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
    public ResponseEntity<VotoResultadoDto> buscarResultadoVotacao(@PathVariable Integer idTopico, @PathVariable Integer idSessao) {
        LOGGER.debug("Buscando resultado da votacao idTopico = {} , idSessao = {} ", idTopico, idSessao);
        VotoResultadoDto votoResultadoDto = service.buscarResultadoVotacao(idTopico, idSessao);
        return ResponseEntity.status(HttpStatus.OK).body(votoResultadoDto);
    }

}
