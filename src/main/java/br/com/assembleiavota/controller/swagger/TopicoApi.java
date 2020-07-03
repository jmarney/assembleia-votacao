package br.com.assembleiavota.controller.swagger;

import br.com.assembleiavota.dto.TopicoDto;
import br.com.assembleiavota.request.TopicoRequest;
import br.com.assembleiavota.response.TopicoResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(value = "Pautas", tags = "Info das Pautas da Assembleia")
public interface TopicoApi {

    @ApiOperation(value = "Lista Pautas",
            notes = "Busca todas as pautas",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição sucesso.",
                    response = TopicoResponse.class)})
    ResponseEntity<TopicoResponse> findAll();

    @ApiOperation(value = "Cria Pauta",
            notes = "Criar uma pauta",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = Void.class)})
    ResponseEntity<Void> create(@ApiParam(name = "Request", required = true) TopicoRequest topicRequest);

    @ApiOperation(value = "Busca Pauta especifica por id",
            notes = "Busca pauta pelo id Pauta",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = TopicoDto.class)})
    ResponseEntity<TopicoDto> findById(@ApiParam(name = "Id Pauta", example = "1", required = true) Long topicoId);


    @ApiOperation(value = "Busca Pauta especifica por nome",
            notes = "Busca pauta pelo nome",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = TopicoDto.class)})
    ResponseEntity<TopicoDto> findByName(@ApiParam(name = "Request", example = "Pauta 1", required = true) String topicoName);
}
