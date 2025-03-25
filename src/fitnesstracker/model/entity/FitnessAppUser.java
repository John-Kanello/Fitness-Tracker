package fitnesstracker.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "fitness_app_users")
public class FitnessAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    private String password;

    public FitnessAppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
