package br.com.assembleiavota.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "VotoResultadoDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoResultadoDto {

    @ApiModelProperty(value = "Objeto TopicoDto com os dados do que foi votado")
    private TopicoDto topicoDto;
    @ApiModelProperty(value = "Objeto VotacaoDto com dados do resultado da votação")
    private VotacaoDto votacaoDto;
}
