package mg.eni.prestation.utils.tests;

import lombok.Data;
import mg.eni.prestation.models.Medecin;

import java.util.List;

@Data
public class TraitementPatient {
    private List<MedecinPatient> patients;
    private Medecin medecin;
    private int total;
}
