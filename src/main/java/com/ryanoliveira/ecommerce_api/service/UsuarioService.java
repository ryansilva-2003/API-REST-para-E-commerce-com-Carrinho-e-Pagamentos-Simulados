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
        if (usuarioRepository.existsByEmail(usuario.getEmal())){
            throw new RuntimeException("Email ja cadastrado: " + usuario.getEmal());
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(UUID id, Usuario usuarioAtualizado){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + id));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmal());
        usuario.setCelular(usuarioAtualizado.getCelular());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
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
