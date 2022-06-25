package mg.eni.prestation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Traitement {
    @EmbeddedId
    @JsonBackReference
    private MedecinPatient id = new MedecinPatient();

    @ManyToOne
    @JoinColumn(name = "medecin")
    @MapsId("medecin")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient")
    @MapsId("patient")
    private Patient patient;

    @Column(nullable = false)
    private int nombreDeJour;

    public Traitement setField(MedecinPatient id, Medecin medecin, Patient patient, int nombreDeJour) {
        this.id = id;
        this.medecin = medecin;
        this.patient = patient;
        this.nombreDeJour = nombreDeJour;
        return this;
    }

    public Traitement setInstance(Medecin medecin, Patient patient, int nombreDeJour) {
        this.nombreDeJour = nombreDeJour;
        this.medecin = medecin;
        this.patient = patient;
        return this;
    }
}
