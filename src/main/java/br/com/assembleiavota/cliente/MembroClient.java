package br.com.assembleiavota.cliente;

import br.com.assembleiavota.exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class MembroClient {

    private static final String ABLE = "ABLE_TO_VOTE";

    /**
     * Consulta na API Client para validar pelo CPF
     */

    public boolean validaAssociadoVotacao(String cpf) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "https://user-info.herokuapp.com/users/{cpf}";
            ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class, cpf);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode status = root.path("status");

            if (ABLE.equals(status.textValue())) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;

        } catch (HttpClientErrorException | IOException ex) {
            throw new NotFoundException("Não foi possivel localizar o CPF do associado");
        }
    }

}
