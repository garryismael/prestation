package mg.eni.prestation.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import mg.eni.prestation.models.Patient;

public interface PatientTraitement {
    @Value("#{target.patients}")
    List<Patient> getPatients();

    @Value("#{target.nombreDeJour}")
    int getNombreDeJour();
}
