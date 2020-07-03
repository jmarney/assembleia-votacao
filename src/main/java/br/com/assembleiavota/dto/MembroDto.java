package br.com.assembleiavota.dto;

import br.com.assembleiavota.model.Membro;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "MembroDTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembroDto {

    private Integer id;

    @ApiModelProperty(value = "CPF válido referente ao associado")
    @CPF(message = "Não é um CPF valido")
    @NotBlank(message = "CPF do associado deve ser preenchido")
    private String cpf;

    @ApiModelProperty(value = "ID da pauta a ser votada")
    @NotNull(message = "oidPauta deve ser preenchido")
    private Integer idTopico;

    public static Membro toEntity(MembroDto membroDto) {
        return Membro.builder()
                .id(membroDto.getId())
                .cpf(membroDto.getCpf())
                .idTopico(membroDto.getIdTopico())
                .build();
    }
}
