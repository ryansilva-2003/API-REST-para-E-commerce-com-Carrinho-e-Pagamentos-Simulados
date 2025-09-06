package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.dto.UsuarioRequestDto;
import com.ryanoliveira.ecommerce_api.model.Usuario;
import com.ryanoliveira.ecommerce_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody @Valid UsuarioRequestDto usuarioRecordDto) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRecordDto, usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));

    }
}
