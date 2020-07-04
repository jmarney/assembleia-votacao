package br.com.assembleiavota.tests.controller;

import br.com.assembleiavota.dto.TopicoDto;
import br.com.assembleiavota.model.Topico;
import br.com.assembleiavota.repository.TopicoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicoControllerTest {

    private static final String url = "/api/v1/topicos";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TopicoRepository repository;

    @Test
    public void cadastraTopico_sucesso() {
        ResponseEntity<TopicoDto> responseEntity = restTemplate.postForEntity(url, new TopicoDto(null, "Testand cadastro do Topico"), TopicoDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void consultaTopico_sucesso() {
        Topico pauta = new Topico(null, "Testando Topico");
        repository.save(pauta);

        ResponseEntity<TopicoDto> responseEntity = restTemplate.getForEntity(url.concat("/{id}"), TopicoDto.class, 1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getDescricao()).isEqualTo(pauta.getDescricao());
    }

    @Test
    public void cadastraTopicoComValoresNulos_erro() {
        ResponseEntity<TopicoDto> responseEntity = restTemplate.postForEntity(url, new TopicoDto(null, null), TopicoDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void consultaTopicoInexistent_erro() {
        Topico topico = new Topico(null, "Teste Pauta 1");
        repository.save(topico);

        ResponseEntity<TopicoDto> responseEntity = restTemplate.getForEntity(url.concat("/{id}"), TopicoDto.class, 2);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody().getDescricao()).isNotEqualTo(topico.getDescricao());
    }
}
