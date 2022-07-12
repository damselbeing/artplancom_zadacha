package zadacha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zadacha.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
    boolean existsUserByName(String name);

    @Modifying
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.name = ?2")
    void updateFailedAttempts(int failAttempts, String name);

}
