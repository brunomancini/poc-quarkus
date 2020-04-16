package br.com.mancini.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mancini.entities.Pessoa;
import br.com.mancini.repositories.PessoasRepository;

@Service
public class PessoasService {
    @Autowired
    PessoasRepository pessoaRepository;

    public List<Pessoa> getAllPessoas() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa createPessoa(final Pessoa pessoa) {
        this.pessoaRepository.save(pessoa);
        return pessoa;
    }
    
    public void deletePessoa(final Long id) {
        this.pessoaRepository.deleteById(id);
    }

    public void updatePessoa(final Pessoa pessoa) {
        this.pessoaRepository.save(pessoa);
    }
}