package br.com.assembleiavota.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("Voto request")
public class VotoRequest {

    private static final long serialVersionUID = 4753561587146476862L;

    @ApiModelProperty(value = "Id da Pauta", example = "1")
    @NotNull(message = "O campo `idPauta` não poder ser nulo")
    @JsonProperty("idPauta")
    private Long topicoId;

    @ApiModelProperty(value = "CPF do Associado", example = "12345678912")
    @NotBlank(message = "O campo `cpf` não poder ser vazio")
    @JsonProperty("cpf")
    @Size(min = 11, max = 11, message = "O campo 'cpf' deve possuir 11 dígitos.")
    private String cpf;

    @ApiModelProperty(value = "Voto", example = "SIM")
    @NotBlank(message = "O campo `voto` não poder ser vazio")
    @JsonProperty("voto")
    private String voto;

    public VotoRequest() {
    }

    public VotoRequest(final Long topicoId, final String cpf, final String voto) {
        this.topicoId = topicoId;
        this.cpf = cpf;
        this.voto = voto;
    }

    public Long getTopicoId() {
        return topicoId;
    }

    public void setTopicoId(Long topicoId) {
        this.topicoId = topicoId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getVote() {
        return voto;
    }

    public void setVoto(String vote) {
        this.voto = voto;
    }

    /*public VoteEnum getVoteEnum() {
        return VoteEnum.find(this.vote).get();
    }*/

    @Override
    public String toString() {
        return "{\"VoteRequest\":{"
                + "\"topicoId\":\"" + topicoId + "\""
                + ", \"cpf\":\"" + cpf + "\""
                + ", \"voto\":\"" + voto + "\""
                + "}}";
    }
}
