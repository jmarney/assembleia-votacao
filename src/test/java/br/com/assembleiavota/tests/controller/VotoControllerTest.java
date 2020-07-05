package br.com.assembleiavota.tests.controller;

import br.com.assembleiavota.dto.VotoDto;
import br.com.assembleiavota.dto.VotoResultadoDto;
import br.com.assembleiavota.exception.BusinessException;
import br.com.assembleiavota.model.Membro;
import br.com.assembleiavota.model.Sessao;
import br.com.assembleiavota.model.Topico;
import br.com.assembleiavota.model.Voto;
import br.com.assembleiavota.repository.MembroRepository;
import br.com.assembleiavota.repository.SessaoRepository;
import br.com.assembleiavota.repository.TopicoRepository;
import br.com.assembleiavota.repository.VotoRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VotoControllerTest {

    private static final String url = "/api/v1/votos";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private VotoRepository repository;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void validVotoCpfInvalido_erro() {

        this.topicoRepository.deleteAll();
        this.sessaoRepository.deleteAll();
        this.membroRepository.deleteAll();

        Topico topico = new Topico(null, "Teste topico descricao");
        topico = this.topicoRepository.save(topico);

        Sessao sessao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), Boolean.TRUE);
        sessao = this.sessaoRepository.save(sessao);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url.concat("/votar"),
                new VotoDto(topico.getId(),
                        sessao.getId(),
                        Boolean.TRUE, "00000"),
                String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void validaVotoSessaoEncerrada_erro() {

        this.topicoRepository.deleteAll();
        this.sessaoRepository.deleteAll();
        this.membroRepository.deleteAll();

        Topico topico = new Topico(null, "Teste topico descricao");
        topico = this.topicoRepository.save(topico);

        Sessao sessao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now(), Boolean.FALSE);
        sessao = this.sessaoRepository.save(sessao);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url.concat("/votar"),
                new VotoDto(topico.getId(),
                        sessao.getId(),
                        Boolean.TRUE,
                        "08892369091"),
                String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
