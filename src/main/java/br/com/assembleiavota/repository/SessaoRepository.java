package br.com.assembleiavota.repository;

import br.com.assembleiavota.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

    Boolean existsSessaoById(Integer id);

    Boolean existsSessaoAtivaByIdAndAtiva(Integer id, Boolean ativa);

    @Query("select s from Sessao s where s.ativa=:ativo")
    List<Sessao> buscaSessoesAtivas(Boolean ativo);

}
