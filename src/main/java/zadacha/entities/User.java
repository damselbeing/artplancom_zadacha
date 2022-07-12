package zadacha.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "user_id")
    private Long idUser;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
//    @JsonIgnore annotation is used to avoid recursion
    private List<Animal> animals;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    public User() {
    }

    public User(Long idUser,
                String name,
                String password) {
        this.idUser = idUser;
        this.name = name;
        this.password = password;
    }
}
