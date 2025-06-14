package com.projeto.examinado.Model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(name="senha_hash", nullable = false)
    private String senhaHash;

    /*
    private String telefone;

    @Column(name="data_cadastro")
    private Date dataCadastro;

    @Column(name="status_conta")
    private String statusConta;

*/

}
