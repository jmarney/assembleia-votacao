package br.com.assembleiavota.tests.unit;

import br.com.assembleiavota.model.Sessao;
import br.com.assembleiavota.repository.SessaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SessaoTest {

    @Autowired
    private SessaoRepository repository;

    @Test
    public void cadastraSessao_sucesso() {
        Sessao sessao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), Boolean.TRUE);
        this.repository.save(sessao);
        assertThat(sessao.getId()).isNotNull();
    }

    @Test
    public void validaListaSessoesEmAdamentoTempo1_minuto_sucesso() {
        Sessao sessao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), Boolean.TRUE);
        this.repository.save(sessao);
        assertThat(this.repository.buscaSessoesAtivas(Boolean.TRUE)).isNotEmpty();
    }

    @Test
    public void validaListaDeSessoesVaziasTempo1_minuto_sucesso() {
        Sessao sessaoVotacao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), Boolean.FALSE);
        this.repository.save(sessaoVotacao);
        assertThat(this.repository.buscaSessoesAtivas(Boolean.TRUE)).isEmpty();
    }

    @Test
    public void validaSessaoExistenteAtiva_sucesso() {
        Sessao sessao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), Boolean.TRUE);
        this.repository.save(sessao);
        assertThat(this.repository.existsSessaoAtivaByIdAndAtiva(sessao.getId(), Boolean.TRUE)).isTrue();
    }

    @Test
    public void validaSessaoExistenteAtivaFalsoPositivo_sucesso() {
        this.repository.deleteAll();
        Sessao sessao = new Sessao(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), Boolean.FALSE);
        this.repository.save(sessao);
        assertThat(this.repository.existsSessaoAtivaByIdAndAtiva(sessao.getId(), Boolean.TRUE)).isFalse();
    }
}
