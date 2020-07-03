package br.com.assembleiavota.dto;

import br.com.assembleiavota.model.Voto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "VotacaoDto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoDto {

    @ApiModelProperty(value = "ID da votação aberta")
    private Integer id;

    @ApiModelProperty(value = "ID da pauta da votação aberta")
    private Integer idTopico;

    @ApiModelProperty(value = "ID da sessão de votação aberta")
    private Integer idSessao;

    @ApiModelProperty(value = "Voto")
    private Boolean voto;

    @ApiModelProperty(value = "Quantidade de votos positivos")
    private Integer qtdVotoSim;

    @ApiModelProperty(value = "Quantidade de votos negativos")
    private Integer qtdVotoNao;

    public static Voto toEntity(VotacaoDto dto) {
        return Voto.builder()
                .id(dto.getId())
                .idTopico(dto.getIdTopico())
                .idSessao(dto.getIdSessao())
                .voto(dto.getVoto())
                .build();
    }
}
