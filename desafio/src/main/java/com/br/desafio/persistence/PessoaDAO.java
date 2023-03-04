package com.br.desafio.persistence;

import com.br.desafio.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PessoaDAO extends JpaRepository<Pessoa, Long> {

  //  @Query("select * from desafio.pessoa p order by numerosms desc")
   // List<Pessoa> findByNumeroSMSDesc();

}
