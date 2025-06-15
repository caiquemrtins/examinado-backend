package com.projeto.examinado.Controller;

import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Service.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {


    @Autowired
    UsuariosService usuariosService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id){
        Usuarios usuario;
        try {
            usuario = usuariosService.getUsuarioById(id);
        } catch (RuntimeException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return  ResponseEntity.ok(usuario);
    }


}
