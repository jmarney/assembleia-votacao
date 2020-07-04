package br.com.assembleiavota.tests.unit;

import br.com.assembleiavota.model.Topico;
import br.com.assembleiavota.repository.TopicoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicoTest {

    @Autowired
    private TopicoRepository repository;

    @Test
    public void validaTopicoTrue() {
        Topico topico = new Topico(null, "Topico testando a descricao");
        this.repository.save(topico);
        assertThat(this.repository.existsByid(topico.getId()).isPresent()).isTrue();
    }

    @Test
    public void validaTopicoFalse() {
        Topico topico = new Topico(null, "Topico testando a descricao");
        this.repository.save(topico);
        assertThat(this.repository.existsByid(3).isPresent()).isFalse();
    }

    @Test
    public void validaCadastroDePautaPersiste() {
        Topico topico = new Topico(null, "Topico testando a descricao");
        this.repository.save(topico);
        assertThat(topico.getId()).isNotNull();
        assertThat(topico.getDescricao()).isEqualTo("Topico testando a descricao");
    }

}
