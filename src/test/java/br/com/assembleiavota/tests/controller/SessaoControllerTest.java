package br.com.assembleiavota.tests.controller;

import br.com.assembleiavota.dto.SessaoAbrirDto;
import br.com.assembleiavota.dto.SessaoDto;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessaoControllerTest {

    private static final String url = "/api/v1/sessao";
    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TopicoRepository topicoRepository;

    @Test
    public void cadastraSessao_sucesso() {
        Topico topico = new Topico(null, "Teste topico sucesso");
        topico = this.topicoRepository.save(topico);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        SessaoAbrirDto sessaoAbrirDto = new SessaoAbrirDto(topico.getId(), null);

        ResponseEntity<SessaoDto> responseEntity = restTemplate.postForEntity(url.concat("/abrir-sessao"), sessaoAbrirDto, SessaoDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getAtiva()).isTrue();
        assertThat(responseEntity.getBody().getDataHoraInicio().format(dateTimeFormatter)).isEqualTo(LocalDateTime.now().format(dateTimeFormatter));
        assertThat(responseEntity.getBody().getDataHoraFim().format(dateTimeFormatter)).isEqualTo(LocalDateTime.now().plusMinutes(1).format(dateTimeFormatter));
    }

    @Test
    public void cadastraSessaoComTempo5_minutos_sucesso() {
        Topico topico = new Topico(null, "Testando o Topico com 5 minutos");
        topico = this.topicoRepository.save(topico);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        SessaoAbrirDto sessaoAbrirDto = new SessaoAbrirDto(topico.getId(), 5);

        ResponseEntity<SessaoDto> responseEntity = restTemplate.postForEntity(url.concat("/abrir-sessao"), sessaoAbrirDto, SessaoDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getAtiva()).isTrue();
        assertThat(responseEntity.getBody().getDataHoraInicio().format(dateTimeFormatter)).isEqualTo(LocalDateTime.now().format(dateTimeFormatter));
        assertThat(responseEntity.getBody().getDataHoraFim().format(dateTimeFormatter)).isEqualTo(LocalDateTime.now().plusMinutes(5).format(dateTimeFormatter));
    }

    @Test
    public void cadastraSessaoComTopicoNull_erro() {
        SessaoAbrirDto sessaoAbrirDto = new SessaoAbrirDto(null, null);

        ResponseEntity<SessaoDto> responseEntity = restTemplate.postForEntity(url.concat("/abrir-sessao"), sessaoAbrirDto, SessaoDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void cadastraSessaoComTopicoInexistente_erro() {
        SessaoAbrirDto sessaoAbrirDto = new SessaoAbrirDto(8, null);

        ResponseEntity<SessaoDto> responseEntity = restTemplate.postForEntity(url.concat("/abrir-sessao"), sessaoAbrirDto, SessaoDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
