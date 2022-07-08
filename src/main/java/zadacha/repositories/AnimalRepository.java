package zadacha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zadacha.entities.Animal;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findAnimalByIdAnimal(Long id);
    Integer deleteAnimalByIdAnimal(Long id);
    boolean existsAnimalByPetName(String name);
}
