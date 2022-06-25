package mg.eni.prestation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinPatient implements Serializable {
    private int medecin;
    private int patient;
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
