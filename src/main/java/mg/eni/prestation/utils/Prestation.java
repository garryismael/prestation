package mg.eni.prestation.utils;

import org.springframework.beans.factory.annotation.Value;

import mg.eni.prestation.models.Medecin;

public interface Prestation {
    @Value("#{target.medecin}")
    Medecin getMedecin();

    @Value("#{target.prestation}")
    int getPrestation();
}