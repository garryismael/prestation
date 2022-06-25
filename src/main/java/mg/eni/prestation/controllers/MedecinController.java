package mg.eni.prestation.controllers;

import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.utils.Prestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/medecins")
public class MedecinController {
    @Autowired
    public MedecinRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prestation> prestations() {
        return repository.prestations();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Medecin create(Medecin medecin) {
        return repository.save(Medecin.setFields(medecin));
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Medecin edit(@PathParam("id") int id, Medecin medecin) {
        Medecin entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(entity.setInstance(medecin.getNom(), medecin.getTauxJournalier()));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        Medecin medecin = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(medecin);
    }
}
