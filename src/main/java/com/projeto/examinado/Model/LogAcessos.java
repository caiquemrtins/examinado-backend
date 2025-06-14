package com.projeto.examinado.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name="log_acessos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogAcessos {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ip_acesso", nullable = false)
    private String ipAcesso;

    @Column(name="time_stamp", nullable = false)
    private Timestamp timeStamp;

    @Column(name ="indicador_login", nullable = false)
    private String indicadorLogin;

}
