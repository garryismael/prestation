package mg.eni.prestation.controllers;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import mg.eni.prestation.forms.TraitementForm;
import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.MedecinPatient;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.models.Traitement;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.repositories.PatientRepository;
import mg.eni.prestation.repositories.TraitementRepository;

@Api(value = "Traitement API")
@Component
@Path("/traitements")
public class TraitementController {
    @Autowired
    private TraitementRepository repository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Traitement.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    public Traitement create(@Valid TraitementForm form) {
        Medecin medecin = medecinRepository.findById(form.getMedecin()).orElseThrow(NotFoundException::new);
        Patient patient = patientRepository.findById(form.getPatient()).orElseThrow(NotFoundException::new);
        MedecinPatient id = new MedecinPatient(form.getMedecin(), form.getPatient());
        if (repository.existsById(id))
            throw new BadRequestException();
        return repository.save(form.traitement(id, medecin, patient));
    }

    @PUT
    @Path("{idMedecin}/{idPatient}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Traitement.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    public Traitement edit(@PathParam("idMedecin") int idMedecin, @PathParam("idPatient") int idPatient,
            @Valid TraitementForm form) {
        Traitement traitement = repository.findById(new MedecinPatient(idMedecin, idPatient))
                .orElseThrow(NotFoundException::new);
        Medecin medecin = medecinRepository.findById(idMedecin).orElseThrow(NotFoundException::new);
        Patient patient = patientRepository.findById(idPatient).orElseThrow(NotFoundException::new);
        return repository.save(traitement.setInstance(medecin, patient, form.getNombreDeJour()));
    }

    @DELETE
    @Path("{medecin}/{patient}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public void delete(@PathParam("medecin") int medecin, @PathParam("patient") int patient) {
        MedecinPatient id = new MedecinPatient(medecin, patient);
        Traitement traitement = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(traitement);
    }
}
