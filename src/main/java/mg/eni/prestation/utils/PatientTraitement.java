package mg.eni.prestation.utils;

import mg.eni.prestation.models.Patient;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface PatientTraitement {
    @Value("#{target.patients}")
    List<Patient> getPatients();
    @Value("#{target.nombreDeJour}")
    int getNombreDeJour();
}
