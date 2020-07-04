package br.com.assembleiavota.tests.unit;

import br.com.assembleiavota.model.Voto;
import br.com.assembleiavota.repository.VotoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VotoTest {

    @Autowired
    VotoRepository repository;

    @Test
    public void voto_sucesso() {
        Voto voto = new Voto(null, Boolean.TRUE, 1, 1);
        this.repository.save(voto);
        assertThat(voto.getId()).isNotNull();
    }

    @Test
    public void validTotalVotos_sucesso() {
        Voto voto = new Voto(null, Boolean.TRUE, 1, 1);
        this.repository.save(voto);
        Voto votoDois = new Voto(null, Boolean.TRUE, 1, 1);
        this.repository.save(votoDois);
        assertThat(this.repository.countVotosByIdTopicoAndIdSessaoAndVoto(1, 1, Boolean.TRUE)).isEqualTo(2);
    }

    @Test
    public void validaTotalVotosIgualZero_sucesso() {
        Voto voto = new Voto(null, Boolean.TRUE, 1, 1);
        this.repository.save(voto);
        assertThat(this.repository.countVotosByIdTopicoAndIdSessaoAndVoto(1, 1, Boolean.FALSE)).isEqualTo(0);
    }
}
