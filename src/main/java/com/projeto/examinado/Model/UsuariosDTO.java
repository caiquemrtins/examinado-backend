package com.projeto.examinado.Model;


import jakarta.validation.constraints.NotBlank;

public record UsuariosDTO(Integer id,
                          @NotBlank String nome,
                          @NotBlank String email,
                          @NotBlank String senhaHash) {
}
