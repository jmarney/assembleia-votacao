package br.com.assembleiavota.dto;

import br.com.assembleiavota.model.Sessao;
import br.com.assembleiavota.request.SessaoRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel(value = "SessaoDto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDto {

    @ApiModelProperty(value = "ID da sessão de votação aberta")
    private Integer id;

    @ApiModelProperty(value = "Data Hora de início da sessão de votação aberta")
    private LocalDateTime dataHoraInicio;

    @ApiModelProperty(value = "Data Hora de fim sessão de votação aberta")
    private LocalDateTime dataHoraFim;

    @ApiModelProperty(value = "Status da sessão de votação aberta")
    private Boolean ativa;


    public static Sessao toEntity(SessaoDto dto) {
        return Sessao.builder()
                .id(dto.getId())
                .dataHoraInicio(dto.getDataHoraInicio())
                .dataHoraFim(dto.getDataHoraFim())
                .ativa(dto.getAtiva())
                .build();
    }

    public static SessaoDto toDto(Sessao sessao) {
        return SessaoDto.builder()
                .id(sessao.getId())
                .dataHoraInicio(sessao.getDataHoraInicio())
                .dataHoraFim(sessao.getDataHoraFim())
                .ativa(sessao.getAtiva())
                .build();
    }
}
