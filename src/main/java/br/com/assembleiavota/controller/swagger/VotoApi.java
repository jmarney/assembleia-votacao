package br.com.assembleiavota.controller.swagger;

import br.com.assembleiavota.request.VotoRequest;
import br.com.assembleiavota.response.VotoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface VotoApi {

    @ApiOperation(value = "Listar todos os Votos",
            notes = "Busca todos os votos",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
                    response = VotoResponse.class)})
    ResponseEntity<VotoResponse> findAll();

    @ApiOperation(value = "Votar",
            notes = "Realiza a votação na determinada pauta",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
                    response = Void.class)})
    ResponseEntity<Void> voto(@ApiParam(name = "Request", required = true) VotoRequest votoRequest);

    @ApiOperation(value = "Busca voto por Id Sessão",
            notes = "Busca voto por um determinado Id Sessão.",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = VotoResponse.class)})
    ResponseEntity<VotoResponse> findBySessaoId(
            @ApiParam(name = "Id Sessão", example = "1", required = true) Long sessaoId);

    @ApiOperation(value = "Busca voto pelo Id ",
            notes = "Busca voto por determinado Id",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = VotoResponse.class)})
    ResponseEntity<VotoResponse> findById(
            @ApiParam(name = "Id do Voto", example = "1", required = true) Long votoId);

    @ApiOperation(value = "Busca voto por CPF",
            notes = "Busca voto por um determinado CPF Associado",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = VotoResponse.class)})
    ResponseEntity<VotoResponse> findByCpf(
            @ApiParam(name = "Request", example = "12345678912", required = true) String cpf);

}
