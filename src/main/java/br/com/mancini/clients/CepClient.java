package br.com.mancini.clients;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//viacep.com.br/ws/01001000/json/ 
@RegisterRestClient
@Path("/ws")
public interface CepClient {

    @GET
    @Path("/{cep}/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 2, delay = 1000)
    public Cep getCep(@PathParam(value = "cep") String cep);
}

