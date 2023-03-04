package com.br.desafio.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private String posicaoAnterior;

    private String posicaoAtual;
    private String numeroSMS;

}
