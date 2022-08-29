package mg.eni.prestation.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.MedecinPatient;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.models.Traitement;

@Data
public class TraitementForm {
    @NotNull
    @Min(1)
    private int medecin;
    @NotNull
    @Min(1)
    private int patient;
    @NotNull
    @Min(1)
    private int nombreDeJour;

    public Traitement traitement(MedecinPatient id, Medecin medecin, Patient patient) {
        return new Traitement().setField(id, medecin, patient, getNombreDeJour());
    }
}
