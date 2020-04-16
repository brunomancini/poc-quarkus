package br.com.mancini.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1")
public class MemoriaResource {

    @GET
    @Path("/memory")
    @Tag(name = "memory")
    @Gauge(name = "quantidade de memoria", unit = MetricUnits.BYTES)
    public Long getMemory() {
        return Runtime.getRuntime().freeMemory();
    }
}