package br.com.assembleiavota.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel("Sessão request")
public class SessaoRequest {

    @ApiModelProperty(value = "Duração em minutos", example = "1")
    @JsonProperty("duracao")
    @Min(value = 1, message = "Duracao deve ser maior que 1")
    private Integer duracao = 1;

    @ApiModelProperty(value = "Id da Pauta", example = "1")
    @NotNull(message = "O campo 'idPauta' não pode ser nulo")
    @JsonProperty("idPauta")
    private Long topicoId;

    public SessaoRequest() {
    }

    public SessaoRequest(final Integer duracao, final Long topicoId) {
        this.duracao = duracao;
        this.topicoId = topicoId;
    }

    public Integer getDuration() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Long getTopicoId() {
        return topicoId;
    }

    public void setTopicoId(Long topicoId) {
        this.topicoId = topicoId;
    }

    @Override
    public String toString() {
        return "{\"SessaoRequest\":{"
                + "\"duracao\":\"" + duracao + "\""
                + ", \"topicId\":\"" + topicoId + "\""
                + "}}";
    }
}
