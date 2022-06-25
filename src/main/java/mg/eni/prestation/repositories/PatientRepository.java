package mg.eni.prestation.repositories;

import mg.eni.prestation.models.Patient;
import mg.eni.prestation.utils.PatientTraitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT t.patient as patients, t.nombreDeJour as nombreDeJour FROM Traitement t WHERE t.id.medecin = ?1")
    List<PatientTraitement> findPatientsByMedecin(int medecin);
}
