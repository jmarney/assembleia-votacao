package br.com.assembleiavota.cliente;

import br.com.assembleiavota.exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class ValidaClient {

    private static final String ABLE = "ABLE_TO_VOTE";

    /**
     * Consulta na API Client para validar pelo CPF
     */

    public boolean validaClient(String cpf) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://user-info.herokuapp.com/users/{cpf}";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, cpf);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode status = mapper.readTree(response.getBody()).path("status");

            return (ABLE.equals(status.textValue())) ? Boolean.TRUE : Boolean.FALSE;

        } catch (HttpClientErrorException | IOException ex) {
            throw new NotFoundException("Não foi possivel localizar o CPF solicitado");
        }
    }

}
