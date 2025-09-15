package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.Usuario;
import com.ryanoliveira.ecommerce_api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RuntimeException("Email ja cadastrado: " + usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(UUID idUsuario, Usuario usuarioAtualizado){
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + idUsuario));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setCelular(usuarioAtualizado.getCelular());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return Optional.ofNullable(usuarioRepository.findByNome(nome));
    }

    @Transactional
    public void deletar(UUID id) {
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuario não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

}
