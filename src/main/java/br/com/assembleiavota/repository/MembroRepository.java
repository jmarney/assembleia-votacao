package br.com.assembleiavota.repository;

import br.com.assembleiavota.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Integer> {

    Boolean validaByCpfAndIdTopico(final String cpf, final  Integer idTopico);
}
