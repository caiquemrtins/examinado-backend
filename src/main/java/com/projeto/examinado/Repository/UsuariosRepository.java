package com.projeto.examinado.Repository;

import com.projeto.examinado.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByEmail(String email);
}
