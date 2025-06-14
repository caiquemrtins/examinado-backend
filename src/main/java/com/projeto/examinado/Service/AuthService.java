package com.projeto.examinado.Service;

import com.projeto.examinado.Enum.IndicadorLogin;
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


        String ipUsuario = IpGetter.getUserIp(request);
        System.out.println("IP:" + ipUsuario);
//        LogAcessos logAcessos = new LogAcessos();
        System.out.println("Indicador: " + IndicadorLogin.INVALIDO.descricao);
        System.out.println("Indicador: " + IndicadorLogin.INVALIDO.name());
        System.out.println("Indicador: " + IndicadorLogin.SENHA_INCORRETA.descricao);
        System.out.println("Indicador: " + IndicadorLogin.SENHA_INCORRETA.name());
//        logAcessos.setIndicadorLogin(IndicadorLogin.INVALIDO.name());

// TO DO : SISTEMA DE LOG DE ACESSOS COM ERROS NAS TENTATIVAS E O IP DO USUARIO!!!!!
        // PEGAR QUAL É O IPV4 AO INVES DO IPV6 NO LOG
        return senhaCorreta;

    }
}
