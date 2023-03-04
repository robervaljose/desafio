package com.br.desafio.service;

import com.br.desafio.controller.dto.PessoaDTO;
import com.br.desafio.domain.Pessoa;
import com.br.desafio.persistence.PessoaDAO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaDAO pessoaDAO;
    private final SMSService smsService;

    public List<Pessoa> getAll (){
        return pessoaDAO.findAll();
    }

    public List<Pessoa> getAllOrderBySMS (){
        return pessoaDAO.findAll(Sort.by(Sort.Direction.DESC, "numeroSMS"));
    }

    public Pessoa getPessoa (Long id){
        return pessoaDAO.findById(id).orElseGet(null);
    }

    public Pessoa salvarPessoa (PessoaDTO pessoaDTO){
        Pessoa pessoa = Pessoa.builder()
                .idade(pessoaDTO.getIdade())
                .nome(pessoaDTO.getNome())
                .numeroSMS(pessoaDTO.getNumeroSMS())
                .build();

        enviarPosicaoViaSMS(pessoa.getNumeroSMS());
        pessoa = pessoaDAO.save(pessoa);

        reorganizarPosicoes();

        return pessoa;
    }

    private void reorganizarPosicoes() {

        List<Pessoa> pessoas = getAllOrderBySMS();
        int sequence=0;
        for(Pessoa p: pessoas){
            sequence++;
            p.setPosicaoAnterior(p.getPosicaoAtual());
            p.setPosicaoAtual(String.valueOf(sequence));
            updatePessoaWithoutSorting(p);
        }


    }

    private void enviarPosicaoViaSMS(String posicao) {
        smsService.sendSMS(posicao);
    }

    public Pessoa updatePessoa (Pessoa pessoa){
        Pessoa p = pessoaDAO.save(pessoa);
        reorganizarPosicoes();
        return p;
    }

    public Pessoa updatePessoaWithoutSorting (Pessoa pessoa){
        Pessoa p = pessoaDAO.save(pessoa);
        return p;
    }

    public void deletePessoa (Long id){
        Pessoa pessoa = getPessoa(id);
        pessoaDAO.delete(pessoa);
    }

    public List<Pessoa> getPessoasSemMudancaDePosicao(){
        List<Pessoa> pessoas = getAll();

        return pessoas.stream()
                .filter(filtrarListaPessoas())
                .collect(Collectors.toList());
    }

    @NotNull
    private static Predicate<Pessoa> filtrarListaPessoas() {
        return pessoa -> pessoa.getPosicaoAtual().equals(pessoa.getPosicaoAnterior()) && pessoa.getPosicaoAnterior()!=null;
    }

}
