package br.com.pedrohfs.avaliacaoredemob.controller;

import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import br.com.pedrohfs.avaliacaoredemob.service.UsuarioService;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.UsuarioCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UsuarioModel usuarioModel){
        try {
            service.salvar(usuarioModel);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
