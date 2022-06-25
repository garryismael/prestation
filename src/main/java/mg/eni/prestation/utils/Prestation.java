package mg.eni.prestation.utils;

import mg.eni.prestation.models.Medecin;
import org.springframework.beans.factory.annotation.Value;

public interface Prestation {
    @Value("#{target.medecin}")
    Medecin getMedecin();
    @Value("#{target.prestation}")
    int getPrestation();
}