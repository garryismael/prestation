package mg.eni.prestation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.eni.prestation.models.MedecinPatient;
import mg.eni.prestation.models.Traitement;

public interface TraitementRepository extends JpaRepository<Traitement, MedecinPatient> {
}
