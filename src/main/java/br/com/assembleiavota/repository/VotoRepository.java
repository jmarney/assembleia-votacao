package br.com.assembleiavota.repository;

import br.com.assembleiavota.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

    Integer countVotosByIdTopicoAndIdSessaoAndVoto(Integer idTopico, Integer idSessao, Boolean voto);

}
