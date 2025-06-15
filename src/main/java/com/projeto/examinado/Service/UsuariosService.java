package com.projeto.examinado.Service;

import com.projeto.examinado.Model.Usuarios;
import com.projeto.examinado.Repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuariosService {

    public final UsuariosRepository usuariosRepository;

    public Optional<Usuarios> getUsuarioById (int id) {
         return usuariosRepository.findById(id);
    }


}
