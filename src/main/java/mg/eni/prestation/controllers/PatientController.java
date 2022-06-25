package mg.eni.prestation.controllers;

import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.repositories.PatientRepository;
import mg.eni.prestation.utils.MedecinPatients;
import mg.eni.prestation.utils.PatientTraitement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("patients")
public class PatientController {
    @Autowired
    private PatientRepository repository;
    @Autowired
    private MedecinRepository medecinRepository;

    @GET
    @Path("{medecin}")
    @Produces(MediaType.APPLICATION_JSON)
    public MedecinPatients traites(@PathParam("medecin") int idMedecin) {
        Medecin medecin = medecinRepository.findById(idMedecin).orElseThrow(NotFoundException::new);
        List<PatientTraitement> patients = repository.findPatientsByMedecin(idMedecin);
        return new MedecinPatients(patients, medecin);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient create(@Valid Patient patient) {
        return repository.save(Patient.setFields(patient));
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient edit(@PathParam("id") int id, Patient patient) {
        Patient model = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(model.setInstance(patient.getNom(),patient.getAdresse()));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        Patient patient = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(patient);
    }
}
