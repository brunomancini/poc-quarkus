package br.com.mancini.services;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mancini.clients.Cep;
import br.com.mancini.clients.CepClient;

@Service
@Traced
public class CepService {
    @Autowired
    @RestClient
    CepClient cepClient;

    public Cep getCep(String cep) {
        return this.cepClient.getCep(cep);
    }
}