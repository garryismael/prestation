package mg.eni.prestation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50, nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String nom;

    @Column(length = 50, nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String adresse;

    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Traitement> traitements = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @ApiModelProperty(hidden = true)
    public List<Traitement> getTraitements() {
        return traitements;
    }

    @ApiModelProperty(hidden = true)
    public void setTraitements(List<Traitement> traitements) {
        this.traitements = traitements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Patient patient = (Patient) o;
        return id != 0 && Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static Patient setFields(Patient patient) {
        patient.setId(-1);
        return patient;
    }

    public Patient setInstance(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        return this;
    }
}
