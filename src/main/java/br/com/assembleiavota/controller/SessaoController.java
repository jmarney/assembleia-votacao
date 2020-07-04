package br.com.assembleiavota.controller;

import br.com.assembleiavota.dto.SessaoAbrirDto;
import br.com.assembleiavota.dto.SessaoDto;
import br.com.assembleiavota.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessao")
@Api(value = "Sessoes", tags = "sessoes")
public class SessaoController  {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoController.class);

    private final SessaoService service;

    @Autowired
    public SessaoController(SessaoService service) {
        this.service = service;
    }

    @ApiOperation(value = "Abrir uma sessão de votação, referente a determinada pauta")
    @PostMapping(value = "/criar-sessao")
    public ResponseEntity<SessaoDto> criarSessao(@Valid @RequestBody SessaoAbrirDto sessaoAbrirDto) {
        LOGGER.debug("Abrindo a sessao  id = {}", sessaoAbrirDto.getIdTopico());
        SessaoDto sessaoDto = service.abrirSessao(sessaoAbrirDto);
        LOGGER.debug("Sessao aberta {} aberta", sessaoAbrirDto.getIdTopico());
        LOGGER.debug("Sessao valida de  {} a {}", sessaoDto.getDataHoraInicio(), sessaoDto.getDataHoraFim());
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoDto);
    }

    @ApiOperation(value = "Busca sessões em andamento")
    @GetMapping(value = "/andamento")
    public ResponseEntity<List<SessaoDto>> buscarSessoesAndamento() {
        LOGGER.debug("Buscando sessoes em andamento");
        List<SessaoDto> sessoesDto = service.buscarSessoesEmAndamento();
        return ResponseEntity.status(HttpStatus.OK).body(sessoesDto);
    }

    @ApiOperation(value = "Busca sessão especifica pelo Id")
    @GetMapping(value = "/busca/{idTopico}")
    public ResponseEntity<SessaoDto> buscarSessaoById(Integer IdTopico) {
        LOGGER.debug("Busca sessão especifica pelo Id");
        SessaoDto sessoesDto = service.buscarSessaoById(IdTopico);
        return ResponseEntity.status(HttpStatus.OK).body(sessoesDto);
    }

}
