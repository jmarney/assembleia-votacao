package br.com.assembleiavota.tests.unit;

import br.com.assembleiavota.model.Topico;
import br.com.assembleiavota.repository.TopicoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
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
