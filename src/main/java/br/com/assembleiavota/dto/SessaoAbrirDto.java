package br.com.assembleiavota.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel(value = "SessaoVotacaoAbrirDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoAbrirDto {

    @ApiModelProperty(value = "ID do Topico que se quer abrir sessão para votação")
    @NotNull(message = "idTopico deve ser preenchido")
    private Integer idTopico;

    @ApiModelProperty(value = "Tempo em MINUTOS que a sessão de votação deverá ficar disponível")
    private Integer tempo;
}
