package com.redhat.training;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.training.service.MultiplierService;
import com.redhat.training.service.SolverService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MultiplierResource implements MultiplierService {
    final Logger log = LoggerFactory.getLogger(MultiplierResource.class);

    @Test
    public void simpleMultiplication() {
	    // Given
	     Mockito.when(solverService.solve("2")).thenReturn(Float.valueOf("2"));
	     Mockito.when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
	     // When
	     Float result = multiplierResource.multiply("2", "3");
	     // Then
	     assertEquals( 6.0f, result )
	}
    @GET
    @Path("/{lhs}/{rhs}")
    @Produces(MediaType.TEXT_PLAIN)
    public Float multiply(@PathParam("lhs") String lhs, @PathParam("rhs") String rhs) {
        log.info("Multiplying {} to {}" ,lhs, rhs);
        return solverService.solve(lhs)*solverService.solve(rhs);
    }
}
