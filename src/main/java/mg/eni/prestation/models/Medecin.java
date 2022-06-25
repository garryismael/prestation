package mg.eni.prestation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Medecin {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50, nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String nom;

    @Column(nullable = false)
    @NotNull
    @Min(1)
    private int tauxJournalier;

    @OneToMany(mappedBy = "medecin",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Traitement> traitements = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medecin medecin = (Medecin) o;
        return id != 0 && Objects.equals(id, medecin.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static Medecin setFields(Medecin medecin) {
        medecin.setId(-1);
        return medecin;
    }

    public Medecin setInstance(String nom, int tauxJournalier) {
        this.nom = nom;
        this.tauxJournalier = tauxJournalier;
        return this;
    }
}
