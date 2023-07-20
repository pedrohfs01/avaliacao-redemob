package br.com.pedrohfs.avaliacaoredemob.controller;

import br.com.pedrohfs.avaliacaoredemob.dto.SolicitacaoDTO;
import br.com.pedrohfs.avaliacaoredemob.model.SolicitacaoModel;
import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import br.com.pedrohfs.avaliacaoredemob.service.SolicitacaoService;
import br.com.pedrohfs.avaliacaoredemob.service.UsuarioService;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.IdadeInvalidaException;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.SolicitacaoAprovadaException;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.SolicitacoesExcedidasException;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.UsuarioCadastradoException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestPart("file") List<MultipartFile> files, @RequestPart("solicitacao")  SolicitacaoDTO solicitacao){
        try {
            service.salvar(solicitacao, files);
        }catch (SolicitacoesExcedidasException | SolicitacaoAprovadaException | IdadeInvalidaException  e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> buscarSolicitacoes(){
        List<SolicitacaoDTO> solicitacoes = service.buscarSolicitacoes();
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{login}")
    public ResponseEntity<List<SolicitacaoDTO>> buscarSolicitacoesPorUsuario(@PathVariable String login){
        List<SolicitacaoDTO> solicitacoes = service.buscarSolicitacoesPorUsuario(login);
        return ResponseEntity.ok(solicitacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarAprovacao(@PathVariable Integer id, @RequestBody Boolean aprovado){
        service.atualizarAprovacao(id, aprovado);
        return ResponseEntity.ok().build();
    }
}
