package br.com.assembleiavota.controller.swagger;

import br.com.assembleiavota.dto.SessaoAbrirDto;
import br.com.assembleiavota.dto.SessaoDto;
import br.com.assembleiavota.request.SessaoRequest;
import br.com.assembleiavota.response.SessaoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessaoApi {

   @ApiOperation(value = "Listar Sessões em andamento",
            notes = "lista todas as sessões em andamento",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Requisição sucesso.",
                    response = SessaoResponse.class)})
    ResponseEntity<List<SessaoDto>> buscarSessoesAndamento();

     @ApiOperation(value = "Buscar a Sessão especifica por id",
            notes = "Busca sessão pelo Id Topico",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = SessaoResponse.class)})
    ResponseEntity<SessaoDto> buscarSessaoById(@ApiParam(name = "Id Topico", example = "1", required = true) Integer idTopico);

    @ApiOperation(value = "Criar Sessão",
            notes = "Criar a Sessão",
            consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Requisição sucesso.",
                    response = Void.class)})
    ResponseEntity<Void> criarSessao(@ApiParam(name = "Request", required = true) SessaoAbrirDto sessaoAbrirDto);


}
