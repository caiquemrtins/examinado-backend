package com.projeto.examinado.Controller;

import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Service.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuariosController {



    private final UsuariosService usuariosService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {

        var usuario = usuariosService.getUsuarioById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário nao encontrado");
        }

        return ResponseEntity.ok(usuario);


    }
}
