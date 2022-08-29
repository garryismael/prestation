package mg.eni.prestation.controllers;

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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.repositories.PatientRepository;
import mg.eni.prestation.utils.MedecinPatients;
import mg.eni.prestation.utils.PatientTraitement;

@Component
@Path("patients")
@Api(value = "Patient API")
public class PatientController {
    @Autowired
    private PatientRepository repository;
    @Autowired
    private MedecinRepository medecinRepository;

    @GET
    @Path("{medecin}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = MedecinPatients.class),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public MedecinPatients traites(@PathParam("medecin") int idMedecin) {
        Medecin medecin = medecinRepository.findById(idMedecin).orElseThrow(NotFoundException::new);
        List<PatientTraitement> patients = repository.findPatientsByMedecin(idMedecin);
        return new MedecinPatients(patients, medecin);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Patient.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    public Patient create(@Valid Patient patient) {
        return repository.save(Patient.setFields(patient));
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Patient.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public Patient edit(@PathParam("id") int id, @Valid Patient patient) {
        Patient model = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(model.setInstance(patient.getNom(), patient.getAdresse()));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public void delete(@PathParam("id") int id) {
        Patient patient = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(patient);
    }
}
