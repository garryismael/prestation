package mg.eni.prestation.utils;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.eni.prestation.models.Medecin;

@Data
@NoArgsConstructor
public class MedecinPatients {
    private List<PatientTraitement> patients;
    private Medecin medecin;
    private int total;

    public MedecinPatients(List<PatientTraitement> patients, Medecin medecin) {
        this.patients = patients;
        this.medecin = medecin;
        setTotal();
    }

    public void setTotal() {
        this.total = 0;
        this.patients.forEach(p -> this.total += p.getNombreDeJour() * this.medecin.getTauxJournalier());
    }
}