package io.github.eduardobatista.rest.resource;

import java.util.ArrayList;

import io.github.eduardobatista.rest.service.ChefBotService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/chefbot")
public class ChefBotResource {
    ChefBotService service = new ChefBotService();

    @POST
    public Response makeRecomedations(ArrayList<Long> tagsId) {
        return Response.ok(service.getRecomendations(tagsId)).build();
    }

}
