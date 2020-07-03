package br.com.assembleiavota.controller.swagger;

import br.com.assembleiavota.request.SessaoRequest;
import br.com.assembleiavota.response.SessaoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface SessaoApi {

    @ApiOperation(value = "Listar Sessões",
            notes = "lista todas as sessões",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição sucesso.",
                    response = SessaoResponse.class)})
    ResponseEntity<SessaoResponse> findAll();

    @ApiOperation(value = "Buscar a Sessão especifica por id",
            notes = "Busca sessão pelo Id Pauta",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = SessaoResponse.class)})
    ResponseEntity<SessaoResponse> findByTopicId(@ApiParam(name = "Id Pauta", example = "1", required = true) Long topicoId);

    @ApiOperation(value = "Criar Sessão",
            notes = "Criar a Sessão",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = Void.class)})
    ResponseEntity<Void> create(@ApiParam(name = "Request", required = true) SessaoRequest sessaoRequest);


}
