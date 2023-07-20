package br.com.pedrohfs.avaliacaoredemob.service;

import br.com.pedrohfs.avaliacaoredemob.dto.SolicitacaoDTO;
import br.com.pedrohfs.avaliacaoredemob.model.SolicitacaoModel;
import br.com.pedrohfs.avaliacaoredemob.model.UsuarioModel;
import br.com.pedrohfs.avaliacaoredemob.repository.SolicitacaoRepository;
import br.com.pedrohfs.avaliacaoredemob.repository.UsuarioRepository;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.IdadeInvalidaException;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.SolicitacaoAprovadaException;
import br.com.pedrohfs.avaliacaoredemob.service.exceptions.SolicitacoesExcedidasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {

    @Autowired
    SolicitacaoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public SolicitacaoService() {

    }

    @Transactional
    public void salvar(SolicitacaoDTO solicitacao, List<MultipartFile> files) {

        UsuarioModel usuario = usuarioRepository.findByCpf(solicitacao.getCpf()).orElse(null);

        LocalDate now = LocalDate.now();
        if (!solicitacao.getDataNascimento().plusYears(18).isBefore(now) && !solicitacao.getDataNascimento().plusYears(18).isEqual(now)) {
            throw new IdadeInvalidaException();
        }

        if (usuario == null) {
            usuario = criarUsuario(solicitacao);
        }

        if (usuario.getSolicitacoes() != null && !usuario.getSolicitacoes().isEmpty()) {
            if (usuario
                    .getSolicitacoes()
                    .stream()
                    .anyMatch(solicitacaoModel -> {
                        if (solicitacaoModel.getAprovado() != null) {
                            return solicitacaoModel.getAprovado().equals(Boolean.TRUE);
                        }
                        return false;
                    })) {
                throw new SolicitacaoAprovadaException();
            }

            if (usuario.getSolicitacoes().size() > 2) {
                throw new SolicitacoesExcedidasException();
            }
        }

        criarSolicitacao(files, usuario);
    }

    private void criarSolicitacao(List<MultipartFile> files, UsuarioModel usuario) {
        SolicitacaoModel solicitacao = new SolicitacaoModel();
        solicitacao.setAprovado(null);

        files.forEach(file -> {
            try {
                if (file.getOriginalFilename().equals("fotoComprovaMoradia.png")) {
                    solicitacao.setFotoComprovaMoradia(file.getBytes());
                }
                if (file.getOriginalFilename().equals("fotoRosto.png")) {
                    solicitacao.setFotoRosto(file.getBytes());
                }
                if (file.getOriginalFilename().equals("fotoDocumento.png")) {
                    solicitacao.setFotoDocumento(file.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        solicitacao.setUsuario(usuario);
        repository.save(solicitacao);
    }

    private UsuarioModel criarUsuario(SolicitacaoDTO solicitacao) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setLogin(solicitacao.getLogin());
        usuarioModel.setSenha(solicitacao.getSenha());
        usuarioModel.setCpf(solicitacao.getCpf());
        usuarioModel.setDataNascimento(solicitacao.getDataNascimento());
        usuarioModel.setNomeMae(solicitacao.getNomeMae());
        usuarioModel.setNome(solicitacao.getNome());
        return usuarioRepository.save(usuarioModel);
    }

    @Transactional
    public List<SolicitacaoDTO> buscarSolicitacoes() {
        List<SolicitacaoModel> solicitacoes = repository.findAllByAprovadoIsNull();
        return solicitacoes.stream().map(solicitacaoModel -> {
            UsuarioModel usuario = solicitacaoModel.getUsuario();
            SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
            solicitacaoDTO.setLogin(usuario.getLogin());
            solicitacaoDTO.setId(solicitacaoModel.getId());
            solicitacaoDTO.setCpf(usuario.getCpf());
            solicitacaoDTO.setDataNascimento(usuario.getDataNascimento());
            solicitacaoDTO.setNome(usuario.getNome());
            solicitacaoDTO.setNomeMae(usuario.getNomeMae());
            solicitacaoDTO.setFotoDocumento(solicitacaoModel.getFotoDocumento());
            solicitacaoDTO.setFotoComprovaMoradia(solicitacaoModel.getFotoComprovaMoradia());
            solicitacaoDTO.setFotoRosto(solicitacaoModel.getFotoRosto());
            return solicitacaoDTO;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void atualizarAprovacao(Integer id, Boolean aprovado) {
        repository.updateAprovacaoPorId(id, aprovado);
    }

    @Transactional
    public List<SolicitacaoDTO> buscarSolicitacoesPorUsuario(String login) {
        UsuarioModel usuario = usuarioRepository.findFirstByLogin(login).orElse(null);
        List<SolicitacaoDTO> solicitacoes = new ArrayList<>();
        if(usuario != null){
            usuario.getSolicitacoes().forEach(solicitacao -> {
                SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
                solicitacaoDTO.setLogin(usuario.getLogin());
                solicitacaoDTO.setId(solicitacao.getId());
                solicitacaoDTO.setCpf(usuario.getCpf());
                solicitacaoDTO.setDataNascimento(usuario.getDataNascimento());
                solicitacaoDTO.setNome(usuario.getNome());
                solicitacaoDTO.setNomeMae(usuario.getNomeMae());
                solicitacaoDTO.setFotoDocumento(solicitacao.getFotoDocumento());
                solicitacaoDTO.setFotoComprovaMoradia(solicitacao.getFotoComprovaMoradia());
                solicitacaoDTO.setFotoRosto(solicitacao.getFotoRosto());
                solicitacaoDTO.setAprovado(solicitacao.getAprovado());
                solicitacoes.add(solicitacaoDTO);
            });
        }
        return solicitacoes;
    }
}
