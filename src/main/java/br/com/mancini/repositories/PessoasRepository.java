package br.com.mancini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mancini.entities.Pessoa;

public interface PessoasRepository extends JpaRepository<Pessoa, Long>{

}
