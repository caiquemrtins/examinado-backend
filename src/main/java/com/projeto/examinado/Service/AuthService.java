package com.projeto.examinado.Service;

import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Model.UsuariosDTO;
import com.projeto.examinado.Model.UsuariosLoginDTO;
import com.projeto.examinado.Repository.UsuariosRepository;
import com.projeto.examinado.Utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    UsuariosRepository usuarioRepository;


    public Usuarios cadastrarUsuario (UsuariosDTO usuarioDTO){
        System.out.println("Entrei no service");
        Usuarios usuario = new Usuarios();
        usuario.setEmail(usuarioDTO.email());
        usuario.setNome(usuarioDTO.nome());
        usuario.setSenhaHash(PasswordHasher.encode(usuarioDTO.senhaHash()));

        return usuarioRepository.save(usuario);

    }

    public boolean login (UsuariosLoginDTO loginDTO){
        Usuarios usuario = usuarioRepository.findByEmail(loginDTO.email())
                .orElseThrow(()-> new RuntimeException("Deu ruim"));

        boolean senhaCorreta = PasswordHasher.encode(loginDTO.senhaHash()).equals(usuario.getSenhaHash());

        if (!senhaCorreta){
            throw new RuntimeException("Senha incorreta");
        }

        return senhaCorreta;

    }
}
