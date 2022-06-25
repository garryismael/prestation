package mg.eni.prestation.controllers;

import mg.eni.prestation.forms.TraitementForm;
import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.MedecinPatient;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.models.Traitement;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.repositories.PatientRepository;
import mg.eni.prestation.repositories.TraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    public Traitement create(TraitementForm form){
        Medecin medecin = medecinRepository.findById(form.getMedecin()).orElseThrow(NotFoundException::new);
        Patient patient = patientRepository.findById(form.getPatient()).orElseThrow(NotFoundException::new);
        MedecinPatient id = new MedecinPatient(form.getMedecin(), form.getPatient());
        if (repository.existsById(id)) throw new BadRequestException();
        return repository.save(form.traitement(id, medecin, patient));
    }

    @PUT
    @Path("{idMedecin}/{idPatient}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Traitement edit(@PathParam("idMedecin") int idMedecin, @PathParam("idPatient") int idPatient, TraitementForm form) {
        Traitement traitement = repository.findById(new MedecinPatient(idMedecin, idPatient)).orElseThrow(NotFoundException::new);
        Medecin medecin = medecinRepository.findById(idMedecin).orElseThrow(NotFoundException::new);
        Patient patient = patientRepository.findById(idPatient).orElseThrow(NotFoundException::new);
        return repository.save(traitement.setInstance(medecin, patient, form.getNombreDeJour()));
    }

    @DELETE
    @Path("{medecin}/{patient}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("medecin") int medecin, @PathParam("patient") int patient) {
        MedecinPatient id = new MedecinPatient(medecin, patient);
        Traitement traitement = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(traitement);
    }
}
