package br.com.assembleiavota.controller;

import br.com.assembleiavota.controller.swagger.TopicoApi;
import br.com.assembleiavota.dto.TopicoDto;
import br.com.assembleiavota.service.TopicoService;
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
import java.util.List;

public class TopicoController implements TopicoApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicoController.class);

    @Autowired
    private TopicoService service;

    @ApiOperation(value = "Criar uma pauta para ser votada")
    @PostMapping
    public ResponseEntity<Void> criarPauta(@Valid @RequestBody TopicoDto topicoDto) {
        LOGGER.debug("Salvando a pauta  = {}", topicoDto.getDescricao());
        service.salvar(topicoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Buscar a pauta utilizando ID")
    @GetMapping(value = "/{idTopico}")
    public ResponseEntity<TopicoDto> buscaPautaById(@PathVariable final Integer idTopico) {
        LOGGER.debug("Buscando a pauta pelo Id = {}", idTopico);
        return ResponseEntity.ok(service.buscarPautaPeloId(idTopico));
    }

    @ApiOperation(value = "Buscar a pauta por Nome")
    @GetMapping(value = "/{idTopico}")
    public ResponseEntity<TopicoDto> buscaPautaByName(@PathVariable final String descricao) {
        LOGGER.debug("Buscando a pauta pela descricao = {}", descricao);
        return ResponseEntity.ok(service.buscarPautaPelaDescricao(descricao));
    }

    @ApiOperation(value = "Busca todas as pautas")
    @GetMapping
    public ResponseEntity<List<TopicoDto>> buscarPautas() {
        LOGGER.debug("Buscando todas as pautas");
        List<TopicoDto> topicoDtos = service.buscarPautas();
        return ResponseEntity.status(HttpStatus.OK).body(topicoDtos);
    }
}
