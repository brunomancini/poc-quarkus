package br.com.mancini.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.mancini.entities.Pessoa;
import br.com.mancini.services.PessoasService;
import io.quarkus.security.identity.SecurityIdentity;


@Path("/v1")
@SecurityScheme(securitySchemeName = "quarkus-auth", type = SecuritySchemeType.OAUTH2, 
flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/quarkus/protocol/openid-connect/token")))
@Tag(name = "pessoas")
@SecurityRequirement(name = "quarkus-auth")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class PessoasResource {
    
    @Inject
    SecurityIdentity context;
    
    @Inject
    PessoasService pessoasService;

    @POST
    @Path("/pessoas")

    @RolesAllowed("user")
    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoasService.createPessoa(pessoa);
    }

    @GET
    @Path("/pessoas")
    @Counted(name = "contador de busca de pessoas")
    @Timed(name = "duracao de busca de pessoas")
    @RolesAllowed("user")
    public List<Pessoa> pessoas() {
        return pessoasService.getAllPessoas();
    }

    @DELETE
    @Path("/pessoas/{id}")
    @RolesAllowed("user")
    public void deletePessoa(@PathParam("id") Long id) {
        pessoasService.deletePessoa(id);
    }

    @PUT
    @Path("/pessoas/{id}")
    @RolesAllowed("user")
    public void putPessoa(Pessoa pessoa) {
        pessoasService.updatePessoa(pessoa);
    }

    @GET
    @Path("/pessoas/token")
    @PermitAll
    public String tokenPessoa() {
        return context.getPrincipal().getName();
    }
}