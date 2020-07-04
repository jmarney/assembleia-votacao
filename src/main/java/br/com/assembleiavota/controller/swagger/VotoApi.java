package br.com.assembleiavota.controller.swagger;

import br.com.assembleiavota.dto.VotoDto;
import br.com.assembleiavota.dto.VotoResultadoDto;
import br.com.assembleiavota.request.VotoRequest;
import br.com.assembleiavota.response.VotoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VotoApi {


    @ApiOperation(value = "Votar",
            notes = "Realiza a votação na determinada pauta",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = Void.class)})
    ResponseEntity<String> votar(@ApiParam(name = "Request", required = true) VotoDto dto);


    @ApiOperation(value = "Busca resultado da votacao",
            notes = "Busca resultado da votacao",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
                    response = VotoResponse.class)})
    ResponseEntity<VotoResultadoDto> resultado(
            @ApiParam(name = "IdTopico", example = "1", required = true) Integer idTopico,
            @ApiParam(name = "IdSessao", example = "1", required = true) Integer idSessao);

}
