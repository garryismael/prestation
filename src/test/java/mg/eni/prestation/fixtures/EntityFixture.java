package mg.eni.prestation.fixtures;

import com.github.javafaker.Faker;
import mg.eni.prestation.constants.DataFixture;
import mg.eni.prestation.models.Medecin;
import mg.eni.prestation.models.MedecinPatient;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.models.Traitement;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.repositories.PatientRepository;
import mg.eni.prestation.repositories.TraitementRepository;

import java.util.ArrayList;
import java.util.List;


public class EntityFixture {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final TraitementRepository traitementRepository;
    private final Faker faker;
    private final int number;
    List<Medecin> medecins = new ArrayList<>();
    List<Patient> patients = new ArrayList<>();

    public EntityFixture(PatientRepository patientRepository, MedecinRepository medecinRepository, TraitementRepository traitementRepository,int number) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.traitementRepository = traitementRepository;
        this.faker = new Faker();
        this.number = number;
    }

    public void createAll() {
        setCostumFixture();
        addPatients();
        addMedecins();
        addTraitements();
    }

    public void addPatients() {
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Patient patient = new Patient();
            patient.setNom(faker.name().fullName());
            String address = faker.address().streetAddress();
            patient.setAdresse(address);
            patients.add(patient);
        }
        patientRepository.saveAllAndFlush(patients);
        this.patients.addAll(patients);
    }

    public void addMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Medecin medecin = new Medecin();
            medecin.setNom(faker.name().fullName());
            medecin.setTauxJournalier(faker.number().numberBetween(1000, 50000));
            medecins.add(medecin);
        }
        medecinRepository.saveAllAndFlush(medecins);
        this.medecins.addAll(medecins);
    }

    public void addTraitements() {
        List<Traitement> traitements = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Traitement traitement = new Traitement();
            Medecin medecin = this.medecins.get(i);
            Patient patient = this.patients.get(i);
            traitement.setId(new MedecinPatient(medecin.getId(), patient.getId()));
            traitement.setMedecin(medecin);
            traitement.setPatient(patient);
            traitement.setNombreDeJour(faker.number().numberBetween(1, 60));
            traitements.add(traitement);
        }
        traitementRepository.saveAllAndFlush(traitements);
    }

    public void setCostumFixture() {
        Medecin medecin = medecinRepository.save(DataFixture.medecin);
        Patient patient = patientRepository.save(DataFixture.patient);
        Traitement traitement = new Traitement();
        MedecinPatient id = new MedecinPatient(medecin.getId(), patient.getId());
        traitementRepository.saveAndFlush(traitement.setField(id, medecin, patient, 10));
    }
}
