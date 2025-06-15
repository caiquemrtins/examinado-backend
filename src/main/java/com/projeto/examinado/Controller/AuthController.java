package com.projeto.examinado.Controller;


import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Model.UsuariosDTO;
import com.projeto.examinado.Model.UsuariosLoginDTO;
import com.projeto.examinado.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastraUsuario (@Valid @RequestBody UsuariosDTO usuarioDTO) {
        try {
            Usuarios novoUsuario = this.authService.cadastrarUsuario(usuarioDTO);
            System.out.println("Novo Usuario:" + novoUsuario);
            return ResponseEntity.ok("Usuario criado:" + novoUsuario.getId() + "  Senha: " + novoUsuario.getSenhaHash());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/teste")
    public String teste() {
        System.out.println("TESTE EXECUTADO");
        return "OK";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody UsuariosLoginDTO login,
                                         HttpServletRequest request){
        System.out.println("Login");
        System.out.println("Header:" + request.getHeaderNames());

        boolean tentativaLogin;
        try {
             tentativaLogin= this.authService.login(login, request);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        if (tentativaLogin){
            return ResponseEntity.ok("Login realizado");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
    }

}
