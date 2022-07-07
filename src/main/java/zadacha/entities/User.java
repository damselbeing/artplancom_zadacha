package zadacha.entities;

import lombok.Data;

import javax.persistence.*;

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
