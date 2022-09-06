package mg.eni.prestation.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Traitement {
    @ApiModelProperty(hidden = true)
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

    @ApiModelProperty(hidden = true)
    public MedecinPatient getId() {
        return id;
    }

    @ApiModelProperty(hidden = true)
    public void setId(MedecinPatient id) {
        this.id = id;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getNombreDeJour() {
        return nombreDeJour;
    }

    public void setNombreDeJour(int nombreDeJour) {
        this.nombreDeJour = nombreDeJour;
    }

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
