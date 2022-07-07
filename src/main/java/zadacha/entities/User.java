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
    private Long idClient;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "password")
    private String password;

    public User() {
    }

    public User(Long idClient, String name, String password) {
        this.idClient = idClient;
        this.name = name;
        this.password = password;
    }
}
