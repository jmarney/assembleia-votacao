package br.com.assembleiavota.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "VotoDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoDto {

    @ApiModelProperty(value = "ID da pauta da votação aberta")
    @NotNull(message = "idPauta deve ser preenchido")
    private Integer IdTopico;

    @ApiModelProperty(value = "ID da sessão de votação aberta")
    @NotNull(message = "idSessao deve ser preenchido")
    private Integer idSessao;

    @ApiModelProperty(value = "Voto")
    @NotNull(message = "Voto deve ser preenchido")
    private Boolean voto;

    @ApiModelProperty(value = "CPF valido")
    @CPF(message = "Não é um CPF valido")
    @NotBlank(message = "cpf do membro deve ser preenchido")
    private String cpf;
}
