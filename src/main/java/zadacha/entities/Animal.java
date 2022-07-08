package zadacha.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "animal_id")
    private Long idAnimal;

    @Column(nullable = false, name = "species")
    private String species;

    @Column(nullable = false, name = "birth_date")
    private String birthDate;

    @Column(nullable = false, name = "sex")
    private String sex;

    @Column(nullable = false, name = "pet_name")
    private String petName;

    public Animal() {
    }

    public Animal(Long idAnimal,
                  String species,
                  String birthDate,
                  String sex,
                  String petName) {
        this.idAnimal = idAnimal;
        this.species = species;
        this.birthDate = birthDate;
        this.sex = sex;
        this.petName = petName;
    }
}
