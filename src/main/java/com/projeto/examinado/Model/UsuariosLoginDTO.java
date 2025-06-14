package com.projeto.examinado.Model;


import jakarta.validation.constraints.NotBlank;

public record UsuariosLoginDTO(@NotBlank String email,
                               @NotBlank String senhaHash) {
}
