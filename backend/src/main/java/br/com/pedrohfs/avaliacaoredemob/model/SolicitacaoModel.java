package br.com.pedrohfs.avaliacaoredemob.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "solicitacao")
@Data
@NoArgsConstructor
public class SolicitacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "aprovado")
    private Boolean aprovado;

    @Column(name = "foto_rosto")
    @Lob
    private byte[] fotoRosto;

    @Column(name = "foto_documento")
    @Lob
    private byte[] fotoDocumento;

    @Column(name = "foto_comprova_moradia")
    @Lob
    private byte[] fotoComprovaMoradia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

}
