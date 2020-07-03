package br.com.assembleiavota.response;

import br.com.assembleiavota.dto.VotoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VotoResponse extends VotoDto implements Serializable {

    private static final long serialVersionUID = -5699561640115032822L;

    @ApiModelProperty("Total os Votos")
    private Integer total = 0;

    @ApiModelProperty("Lista os Votos")
    @JsonProperty("votos")
    private List<VotoResponse> votos = new ArrayList<>();

    public VotoResponse() {
    }

    public VotoResponse(Integer total, List<VotoResponse> votos) {
        this.total = total;
        this.votos = votos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<VotoResponse> getVotos() {
        return votos;
    }

    public void setVotes(List<VotoResponse> votes) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "{\"VotoListaResponse\":{"
                + "\"total\":\"" + total + "\""
                + ", \"votos\":" + votos
                + "}}";
    }

    /*public String getVoto() {
        return VoteEnum.find(vote)
                .map(VoteEnum::getValue)
                .orElse(vote);
    }

    @Override
    public String toString() {
        return "{\"VoteResponse\":"
                + super.toString()
                + ", \"voto\":\"" + voto + "\""
                + "}";
    }*/
}
