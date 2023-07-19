package br.com.pedrohfs.avaliacaoredemob.config;

import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import br.com.pedrohfs.avaliacaoredemob.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        UsuarioModel u1 = new UsuarioModel();
        u1.setUsername("pedro");
        u1.setPassword("123");

        usuarioRepository.save(u1);

    }
}
