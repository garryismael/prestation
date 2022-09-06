package mg.eni.prestation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.utils.Prestation;

public interface MedecinRepository extends JpaRepository<Medecin, Integer> {
    @Query("SELECT m AS medecin, COALESCE(SUM(t.nombreDeJour*m.tauxJournalier), 0) AS prestation from Medecin m LEFT JOIN Traitement t ON m.id = t.id.medecin GROUP BY t.medecin, m.id")
    List<Prestation> prestations();
}
