package br.com.assembleiavota.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("Topico request")
public class TopicoRequest {

    private static final long serialVersionUID = -8458575684129896178L;

    @ApiModelProperty(value = "Nome da Pauta", example = "Pauta exemplo 1")
    @NotBlank(message = "O campo `nome` não poder ser vazio")
    @JsonProperty("nome")
    private String nome;

    public TopicoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "{\"TopicoRequest\":{"
                + "\"nome\":\"" + nome + "\""
                + "}}";
    }
}
