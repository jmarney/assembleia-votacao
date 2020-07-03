package br.com.assembleiavota.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("Sessões response")
public class SessaoResponse {

    @ApiModelProperty("Total de Sessões")
    private Integer total = 0;

    @ApiModelProperty("Lista de Sessões")
    @JsonProperty("sessoes")
    private List<SessaoResponse> sessoes = new ArrayList<>();

    public SessaoResponse() {}

    public SessaoResponse(Integer total, List<SessaoResponse> sessoesResponse) {
        this.total = total;
        this.sessoes = sessoesResponse;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SessaoResponse> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<SessaoResponse> sessions) {
        this.sessoes = sessions;
    }

    @Override
    public String toString() {
        return "{\"SessoesResponse\":{"
                + "\"total\":\"" + total + "\""
                + ", \"sessions\":" + sessoes
                + "}}";
    }

}
