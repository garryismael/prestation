package mg.eni.prestation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.eni.prestation.models.Patient;
import mg.eni.prestation.utils.PatientTraitement;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT t.patient as patients, t.nombreDeJour as nombreDeJour FROM Traitement t WHERE t.id.medecin = ?1")
    List<PatientTraitement> findPatientsByMedecin(int medecin);
}
