package br.com.assembleiavota.controller.swagger;

import br.com.assembleiavota.dto.TopicoDto;
import br.com.assembleiavota.request.TopicoRequest;
import br.com.assembleiavota.response.TopicoResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "Pautas", tags = "Info das Pautas da Assembleia")
public interface TopicoApi {

    @ApiOperation(value = "Lista Pautas",
            notes = "Busca todas as pautas",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição sucesso.",
                    response = TopicoResponse.class)})
    ResponseEntity<List<TopicoDto>> buscarPautas();

    @ApiOperation(value = "Cria Pauta",
            notes = "Criar uma pauta",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = Void.class)})
    ResponseEntity<Void> criarPauta(@ApiParam(name = "Request", required = true) TopicoDto topicoDto);

    @ApiOperation(value = "Busca Pauta por id",
            notes = "Busca pauta pelo id Topico",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = TopicoDto.class)})
    ResponseEntity<TopicoDto> buscaPautaById(@ApiParam(name = "Id Topico", example = "1", required = true) Integer idTopico);


    @ApiOperation(value = "Busca Pauta especifica por descricao",
            notes = "Busca pauta pela descricao",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = TopicoDto.class)})
    ResponseEntity<TopicoDto> buscaPautaByName(@ApiParam(name = "Request", example = "Pauta 1", required = true) String descricao);
}
