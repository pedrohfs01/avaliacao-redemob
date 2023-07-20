package br.com.pedrohfs.avaliacaoredemob.dto;

import lombok.Data;

import javax.persistence.Lob;
import java.time.LocalDate;

@Data
public class SolicitacaoDTO {

    private Integer id;
    private String login;
    private String senha;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String nomeMae;
    @Lob
    private byte[] fotoRosto;
    @Lob
    private byte[] fotoDocumento;
    @Lob
    private byte[] fotoComprovaMoradia;

    private Boolean aprovado;
}
