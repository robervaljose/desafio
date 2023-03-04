package com.br.desafio.controller;

import com.br.desafio.controller.dto.PessoaDTO;
import com.br.desafio.domain.Pessoa;
import com.br.desafio.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getPessoas(){
        return new ResponseEntity<>(pessoaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoas(@PathVariable("id") Long id){
        return new ResponseEntity<>(pessoaService.getPessoa(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody PessoaDTO pessoa){
       return new ResponseEntity<>(pessoaService.salvarPessoa(pessoa), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa){
        return new ResponseEntity<>(pessoaService.updatePessoa(pessoa), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable("id") Long id){
        pessoaService.deletePessoa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sem-alteracao-de-posicao")
    public ResponseEntity<List<Pessoa>> getPessoasSemAlteracao(){
        return new ResponseEntity<>(pessoaService.getPessoasSemMudancaDePosicao(), HttpStatus.OK);
    }

}

