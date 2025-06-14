package com.projeto.examinado.Enum;

import lombok.Getter;

@Getter
public enum IndicadorLogin {
    VALIDO("Válido"),
    INVALIDO("Inválido"),
    SENHA_INCORRETA("Senha incorreta");


    public final String descricao;

    IndicadorLogin(String descricao) {
        this.descricao=descricao;
    }


}
