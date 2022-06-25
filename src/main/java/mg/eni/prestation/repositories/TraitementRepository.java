package mg.eni.prestation.repositories;

import mg.eni.prestation.models.MedecinPatient;
import mg.eni.prestation.models.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitementRepository extends JpaRepository<Traitement, MedecinPatient> { }
