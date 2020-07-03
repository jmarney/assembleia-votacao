package br.com.assembleiavota.dto;

import br.com.assembleiavota.model.Topico;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "PautaDTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDto {

    @ApiModelProperty(value = "ID Pauta", required = true)
    private Integer id;

    @ApiModelProperty(value = "Descrição referente o que será votado")
    @NotBlank(message = "Descrição deve ser preenchido")
    private String descricao;

    public static Topico toEntity(TopicoDto dto) {
        return Topico.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .build();
    }

    public static TopicoDto toDTO(Topico topico) {
        return TopicoDto.builder()
                .id(topico.getId())
                .descricao(topico.getDescricao())
                .build();
    }
}
