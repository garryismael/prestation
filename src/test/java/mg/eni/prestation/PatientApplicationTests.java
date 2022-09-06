package mg.eni.prestation;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import mg.eni.prestation.constants.DataFixture;
import mg.eni.prestation.controllers.PatientController;
import mg.eni.prestation.fixtures.EntityFixture;
import mg.eni.prestation.models.Patient;
import mg.eni.prestation.repositories.MedecinRepository;
import mg.eni.prestation.repositories.PatientRepository;
import mg.eni.prestation.repositories.TraitementRepository;
import mg.eni.prestation.utils.MedecinPatients;
import mg.eni.prestation.utils.PatientTraitement;
import mg.eni.prestation.utils.tests.TraitementPatient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PatientApplicationTests extends JerseyTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private TraitementRepository traitementRepository;
    @Autowired
    private PatientController patientController;

    private static RestTemplate restTemplate;
    private String baseUrl = "http://localhost";

    @LocalServerPort
    int port;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    public static EntityFixture fixture;

    @Override
    protected Application configure() {
        return new ResourceConfig(PatientController.class);
    }

    @BeforeEach
    public void setUp() {
        this.baseUrl = String.format("%s:%d/api/patients", baseUrl, port);
    }

    @BeforeEach
    public void initDb() {
        fixture = new EntityFixture(patientRepository, medecinRepository, traitementRepository, 50);
        fixture.createAll();
    }

    @Test
    void repositoryPatientsTraitesParUnMedecin() {
        List<PatientTraitement> traitements = patientRepository.findPatientsByMedecin(1);
        Assertions.assertEquals(1, traitements.size());
        Assertions.assertEquals(10, traitements.get(0).getNombreDeJour());
        Assertions.assertEquals(1, traitements.get(0).getPatients().size());
        Assertions.assertEquals(traitements.get(0).getPatients().get(0).getNom(), DataFixture.patient.getNom());
    }

    @Test
    void traites() {
        MedecinPatients medecinPatients = patientController.traites(1);
        Assertions.assertEquals(medecinPatients.getPatients().get(0).getPatients().get(0).getNom(),
                DataFixture.patient.getNom());
    }

    @Test
    void GetTraitements() {
        var response = restTemplate.getForEntity(String.format("%s/1", baseUrl), TraitementPatient.class);
        Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void createPatient() {
        Patient patient = new Patient().setInstance("Garry", "Tanambao");
        var response = restTemplate.postForEntity(baseUrl, patient, Patient.class);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getNom(), "Garry");
    }

    @Test
    void traitesNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> patientController.traites(1000));
    }
}
