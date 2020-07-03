package br.com.assembleiavota.repository;

import br.com.assembleiavota.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    Optional<Boolean> existsByid(Integer id);
}
