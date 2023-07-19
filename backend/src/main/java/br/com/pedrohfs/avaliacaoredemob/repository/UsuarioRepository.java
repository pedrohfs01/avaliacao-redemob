package br.com.pedrohfs.avaliacaoredemob.repository;

import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByUsername(String username);

    boolean existsByUsername(String username);
}
