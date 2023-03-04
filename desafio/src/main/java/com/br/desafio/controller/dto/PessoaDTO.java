package com.br.desafio.controller.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaDTO {
    private String nome;
    private Integer idade;
    private String numeroSMS;
}
