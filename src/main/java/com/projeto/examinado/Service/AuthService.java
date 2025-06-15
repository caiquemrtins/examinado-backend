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

import java.sql.Timestamp;



@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UsuariosRepository usuarioRepository;

    @Autowired
    private final LogAcessosRepository logAcessosRepository;


    public Usuarios cadastrarUsuario (UsuariosDTO usuarioDTO){

//      No Optional, nao ve se o retorno é nulo ou não nulo, até porque o optional nao devolve nulo
//      Então devemos testar se isPresent ou isEmpty ao inves de testar se é nulo.
        if (usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()){
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
        LogAcessos logAcessos = new LogAcessos();

        logAcessos.setUsuario(usuario);
        logAcessos.setIpAcesso(ipUsuario);

//       Pego o timestamp atual e coloco no log.
        logAcessos.setTimeStamp(new Timestamp(System.currentTimeMillis()));


        if (senhaCorreta){
            logAcessos.setIndicadorLogin(IndicadorLogin.VALIDO.descricao);
        } else {
            logAcessos.setIndicadorLogin(IndicadorLogin.SENHA_INCORRETA.descricao);
        }


        logAcessosRepository.save(logAcessos);


        return senhaCorreta;

    }
}
