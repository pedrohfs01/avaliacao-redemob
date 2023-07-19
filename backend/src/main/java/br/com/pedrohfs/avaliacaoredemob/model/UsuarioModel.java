package br.com.pedrohfs.avaliacaoredemob.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;

    @Column(name = "senha")
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;
}
