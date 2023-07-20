package br.com.pedrohfs.avaliacaoredemob.repository;

import br.com.pedrohfs.avaliacaoredemob.model.SolicitacaoModel;
import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, Integer> {


    List<SolicitacaoModel> findAllByAprovadoIsNull();

    @Modifying
    @Query("UPDATE solicitacao s SET s.aprovado = :aprovado WHERE s.id = :id")
    void updateAprovacaoPorId(@Param("id") Integer id, @Param("aprovado") Boolean aprovado);

}
