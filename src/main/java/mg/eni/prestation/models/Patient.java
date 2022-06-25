package mg.eni.prestation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50, nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String nom;

    @Column(length = 50, nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String adresse;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Traitement> traitements = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
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
