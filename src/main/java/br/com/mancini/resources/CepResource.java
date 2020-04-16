package br.com.mancini.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.mancini.clients.Cep;
import br.com.mancini.services.CepService;
import io.quarkus.cache.CacheResult;

@Path("/ceps")
@Tag(name = "ceps")
@Produces(value = MediaType.APPLICATION_JSON)
public class CepResource {
    @Inject
    CepService cepService;
    
    @GET
    @Path("/{cep}")
    @CacheResult(cacheName = "ceps", lockTimeout = 10)
    public Cep getCep(@PathParam("cep") String cep) {
        return cepService.getCep(cep);
    }
}