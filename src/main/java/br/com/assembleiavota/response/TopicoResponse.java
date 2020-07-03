package br.com.assembleiavota.response;

import br.com.assembleiavota.dto.TopicoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("Topicos response")
public class TopicoResponse {

    @ApiModelProperty("Total de Pautas")
    private Integer total = 0;

    @ApiModelProperty("Lista de Pautas")
    @JsonProperty("pautas")
    private List<TopicoDto> topicosDto = new ArrayList<>();

    public TopicoResponse() {
    }

    public TopicoResponse(Integer total, List<TopicoDto> topicDtos) {
        this.total = total;
        this.topicosDto = topicDtos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<TopicoDto> getTopicosDto() {
        return topicosDto;
    }

    public void setTopicoDto(List<TopicoDto> topicosDto) {
        this.topicosDto = topicosDto;
    }

    @Override
    public String toString() {
        return "{\"TopicoResponse\":{"
                + "\"total\":\"" + total + "\""
                + ", \"topicDtos\":" + topicosDto
                + "}}";
    }
}
