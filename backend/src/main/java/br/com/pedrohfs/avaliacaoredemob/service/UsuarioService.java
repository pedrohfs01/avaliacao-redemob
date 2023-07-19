package br.com.pedrohfs.avaliacaoredemob.service;

import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import br.com.pedrohfs.avaliacaoredemob.repository.UsuarioRepository;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.UsuarioCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository repo;


    public UsuarioModel salvar(UsuarioModel usuarioModel){
        boolean exists = repo.existsByUsername(usuarioModel.getUsername());
        if(exists){
            throw new UsuarioCadastradoException();
        }
        return repo.save(usuarioModel);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = repo.findByUsername(username)
                              .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));
        return User.builder()
                .username(usuarioModel.getUsername())
                .password(usuarioModel.getPassword())
                .roles("USER")
                .build()
                ;
    }
}
