package br.com.assembleiavota.controller;

import br.com.assembleiavota.dto.TopicoDto;
import br.com.assembleiavota.service.TopicoService;
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
@RequestMapping(value = "/api/v1/topicos")
@Api(value = "Topicos", tags = "topicos")
public class TopicoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicoController.class);

    @Autowired
    private TopicoService service;

    @ApiOperation(value = "Criar uma pauta para ser votada")
    @PostMapping
    public ResponseEntity<TopicoDto> criarPauta(@Valid @RequestBody TopicoDto topicoDto) {
        LOGGER.debug("Salvando a pauta  = {}", topicoDto.getDescricao());
        TopicoDto dto = service.salvar(topicoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @ApiOperation(value = "Buscar a pauta utilizando ID")
    @GetMapping(value = "/{idTopico}")
    public ResponseEntity<TopicoDto> buscarPautaPeloId(@PathVariable Integer idTopico) {
        LOGGER.debug("Buscando a pauta pelo Id = {}", idTopico);
        return ResponseEntity.ok(service.buscarPautaPeloId(idTopico));
    }

    @ApiOperation(value = "Busca todas as pautas")
    @GetMapping(value = "/topicos")
    public ResponseEntity<List<TopicoDto>> buscarPautas() {
        LOGGER.debug("Buscando todas as pautas");
        List<TopicoDto> topicoDtos = service.buscarPautas();
        return ResponseEntity.status(HttpStatus.OK).body(topicoDtos);
    }
}
