package mg.eni.prestation.constants;

import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.Patient;

public class DataFixture {
    public static final Patient patient = new Patient().setInstance("Ronaldo", "Manchester United");
    public static final Medecin medecin = new Medecin().setInstance("Garry", 1000);
}
