package com.projeto.examinado.Controller;


import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Model.UsuariosDTO;
import com.projeto.examinado.Model.UsuariosLoginDTO;
import com.projeto.examinado.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastraUsuario (@Valid @RequestBody UsuariosDTO usuarioDTO) {
        System.out.println("Entrei no projeto");
        Usuarios novoUsuario = this.authService.cadastrarUsuario(usuarioDTO);
        System.out.println(novoUsuario);
        return ResponseEntity.ok("Usuario criado:" + novoUsuario.getId() + "  Senha: " + novoUsuario.getSenhaHash());
    }

    @GetMapping("/teste")
    public String teste() {
        System.out.println("TESTE EXECUTADO"); // Aparece no console?
        return "OK";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody UsuariosLoginDTO login){
        System.out.println("Login");
        boolean tentativaLogin;
        try {
             tentativaLogin= this.authService.login(login);
        }
        catch (Exception e){
            tentativaLogin=false;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        if (tentativaLogin){
            return ResponseEntity.ok("Login realizado");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha incorreta");
    }

}
