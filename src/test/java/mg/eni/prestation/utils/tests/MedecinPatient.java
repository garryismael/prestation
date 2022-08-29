package mg.eni.prestation.utils.tests;

import java.util.List;

import lombok.Data;
import mg.eni.prestation.models.Patient;

@Data
public class MedecinPatient {
    private List<Patient> patients;
    private int nombreDeJour;
}
