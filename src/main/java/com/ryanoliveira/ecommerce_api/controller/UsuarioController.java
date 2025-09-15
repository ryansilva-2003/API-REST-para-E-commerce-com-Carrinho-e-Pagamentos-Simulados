package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.dto.UsuarioRequestDto;
import com.ryanoliveira.ecommerce_api.dto.UsuarioResponseDto;
import com.ryanoliveira.ecommerce_api.model.Usuario;
import com.ryanoliveira.ecommerce_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping ("/usuario")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        ;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable UUID idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);

        UsuarioResponseDto dto = new UsuarioResponseDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCelular(),
                usuario.getDataNasc()
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable UUID idUsuario, @Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setSenha(usuarioRequestDto.senha());
        usuario.setCelular(usuarioRequestDto.celular());

        Usuario atualizado = usuarioService.atualizar(idUsuario, usuario);

        UsuarioResponseDto dto = new UsuarioResponseDto(
                atualizado.getIdUsuario(),
                atualizado.getNome(),
                atualizado.getEmail(),
                atualizado.getCelular(),
                atualizado.getDataNasc()
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> salvar(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setSenha(usuarioRequestDto.senha());
        usuario.setDataNasc(usuarioRequestDto.dataNasc());
        usuario.setCelular(usuarioRequestDto.celular());

        Usuario novoUsuario = usuarioService.save(usuario);

        UsuarioResponseDto dto = new UsuarioResponseDto(
                novoUsuario.getIdUsuario(),
                novoUsuario.getNome(),
                novoUsuario.getEmail(),
                novoUsuario.getCelular(),
                novoUsuario.getDataNasc()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletar (@PathVariable UUID idUsuario){
        usuarioService.deletar(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        List<UsuarioResponseDto> usuariosDto = usuarios.stream()
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getCelular(),
                        usuario.getDataNasc()
                ))
                .toList();
        return ResponseEntity.ok(usuariosDto);

    }

}