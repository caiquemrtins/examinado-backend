package com.projeto.examinado.Model;


import jakarta.validation.constraints.NotBlank;

public record UsuariosLoginDTO(@NotBlank(message = "O email é obrigatório")
                               String email,

                               @NotBlank
                               String senhaHash) {
}
