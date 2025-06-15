package com.projeto.examinado.Service;

import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuariosService {

    @Autowired
    public final UsuariosRepository usuariosRepository;

    public Usuarios getUsuarioById (int id){
        return usuariosRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
    }


}
