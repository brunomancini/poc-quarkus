package br.com.mancini.fault;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "fault")
@Path("/fault")
@Produces(value = MediaType.TEXT_PLAIN)
@Consumes(value = MediaType.TEXT_PLAIN)
public class Fault {
    @GET
    @Path("/retry")
    @Retry(maxRetries = 3, delay = 1000)
    @Fallback(fallbackMethod = "fallbackMethod")
    public String retry() {
        Integer numero = new Random().nextInt(10);
        if (numero > 5) {
            return "RETRY";
        } 
        System.out.println("RETRY");
        throw new IllegalArgumentException();
    }

    @GET
    @Path("/timeout")
    @Timeout(1000)
    public String timeout() throws InterruptedException {
        Integer numero = new Random().nextInt(10);
        if (numero > 5) {
            return "TIMEOUT OK";
        } 
        Thread.sleep(2000);
        return null;
    }

    @GET
    @Path("/circuit-break")
    @CircuitBreaker(requestVolumeThreshold = 2, failureRatio = 0.1, delay = 2000)
    public String circuitBreak() throws Exception {
        Integer numero = new Random().nextInt(10);
        if (numero > 5) {
            return "CIRCUIT BREAK OK";
        } 
        throw new Exception();
    }

    @GET
    @Path("/fallback")
    @Fallback(fallbackMethod = "fallbackMethod")
    @Timeout(value = 1000)
    public String fallback() throws InterruptedException {
        Integer numero = new Random().nextInt(10);
        if (numero > 5) {
            return "FALLBACK OK";
        } 
        Thread.sleep(2000);
        return null;
    }

    public String fallbackMethod() {
        return "PASSOU PELO FALLBACK";
    }
}