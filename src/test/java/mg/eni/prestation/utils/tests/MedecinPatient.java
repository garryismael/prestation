package mg.eni.prestation.utils.tests;

import lombok.Data;
import mg.eni.prestation.models.Patient;

import java.util.List;

@Data
public class MedecinPatient {
    private List<Patient> patients;
    private int nombreDeJour;
}
