package fitnesstracker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "fitness_user_applications")
public class UserApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(unique = true)
    private String apiKey;
    @JsonIgnore
    private LocalDateTime registrationDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private FitnessAppUser fitnessAppUser;

    public UserApplication(String name, String description, String apiKey, LocalDateTime registrationDate) {
        this.name = name;
        this.description = description;
        this.apiKey = apiKey;
        this.registrationDate = registrationDate;
    }
}
