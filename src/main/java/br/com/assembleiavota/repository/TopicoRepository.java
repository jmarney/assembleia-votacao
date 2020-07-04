package br.com.assembleiavota.repository;

import br.com.assembleiavota.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    Boolean existsByid(Integer id);

}
