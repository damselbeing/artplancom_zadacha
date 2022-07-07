package zadacha.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zadacha.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByName (String name);

    User getUserByNameAndPassword (String name, String password);
}
