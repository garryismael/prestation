package mg.eni.prestation.utils.tests;

import java.util.List;

import lombok.Data;
import mg.eni.prestation.models.Medecin;

@Data
public class TraitementPatient {
    private List<MedecinPatient> patients;
    private Medecin medecin;
    private int total;
}
