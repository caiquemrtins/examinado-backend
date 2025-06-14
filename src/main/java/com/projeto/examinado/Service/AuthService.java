package com.projeto.examinado.Service;

import com.projeto.examinado.Model.LogAcessos;
import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Model.UsuariosDTO;
import com.projeto.examinado.Model.UsuariosLoginDTO;
import com.projeto.examinado.Repository.LogAcessosRepository;
import com.projeto.examinado.Repository.UsuariosRepository;
import com.projeto.examinado.Utils.IpGetter;
import com.projeto.examinado.Utils.PasswordHasher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UsuariosRepository usuarioRepository;

    @Autowired
    private final LogAcessosRepository logAcessosRepository;


    public Usuarios cadastrarUsuario (UsuariosDTO usuarioDTO){
        System.out.println("Entrei no service");
        if (usuarioRepository.findByEmail(usuarioDTO.email()) != null){
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuarios usuario = new Usuarios();
        usuario.setEmail(usuarioDTO.email());
        usuario.setNome(usuarioDTO.nome());
        usuario.setSenhaHash(PasswordHasher.encode(usuarioDTO.senhaHash()));

        return usuarioRepository.save(usuario);

    }

    public boolean login (UsuariosLoginDTO loginDTO, HttpServletRequest request){
        Usuarios usuario = usuarioRepository.findByEmail(loginDTO.email())
                .orElseThrow(()-> new RuntimeException("E-mail não encontrado!"));

        boolean senhaCorreta = PasswordHasher.encode(loginDTO.senhaHash()).equals(usuario.getSenhaHash());

        if (!senhaCorreta){
            throw new RuntimeException("Senha incorreta");
        }

        String ipUsuario = IpGetter.getUserIp(request);
        System.out.println("IP:" + ipUsuario);


        return senhaCorreta;

    }
}
