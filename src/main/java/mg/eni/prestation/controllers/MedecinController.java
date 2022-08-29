package mg.eni.prestation.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.utils.Prestation;

@Component
@Api(value = "Medecin API")
@Path("/medecins")
public class MedecinController {
    @Autowired
    public MedecinRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Prestation List", response = Prestation.class, responseContainer = "List")
    public List<Prestation> prestations() {
        return repository.prestations();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Medecin.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    public Medecin create(@Valid Medecin medecin) {
        return repository.save(Medecin.setFields(medecin));
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Medecin.class),
            @ApiResponse(code = 400, message = "Bad Request", response = HashMap.class, responseContainer = "HashMap<String,String>"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public Medecin edit(@PathParam("id") int id, @Valid Medecin medecin) {
        Medecin entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(entity.setInstance(medecin.getNom(), medecin.getTauxJournalier()));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public void delete(@PathParam("id") int id) {
        Medecin medecin = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(medecin);
    }
}
