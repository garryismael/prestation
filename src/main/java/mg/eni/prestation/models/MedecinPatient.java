package mg.eni.prestation.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MedecinPatient implements Serializable {
    private int medecin;
    private int patient;

    public MedecinPatient() {
    }

    public MedecinPatient(int medecin, int patient) {
        this.medecin = medecin;
        this.patient = patient;
    }

    public int getMedecin() {
        return medecin;
    }

    public void setMedecin(int medecin) {
        this.medecin = medecin;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedecinPatient ids = (MedecinPatient) o;
        return medecin == ids.getMedecin() && patient == ids.getPatient();
    }

    @Override
    public int hashCode() {
        return Objects.hash(medecin, patient);
    }
}
