package org.acme;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.Set;

@Path("/hello")
public class GreetingResource {

    @Inject
    Validator validator;

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(
            @RequestBody(content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Book.class)))
            Book book
    ) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("malformed book",violations);
        } else {
            return "No violations.";
        }
    }
}
