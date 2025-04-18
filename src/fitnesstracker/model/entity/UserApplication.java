package fitnesstracker.model.entity;

import fitnesstracker.model.enums.ApplicationCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_applications")
public class UserApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "api_key", unique = true)
    private String apiKey;
    private LocalDateTime registrationDate;
    private ApplicationCategory applicationCategory;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private FitnessAppUser fitnessAppUser;

    public UserApplication(String name,
                           String description,
                           String apiKey,
                           LocalDateTime registrationDate,
                           ApplicationCategory applicationCategory) {
        this.name = name;
        this.description = description;
        this.apiKey = apiKey;
        this.registrationDate = registrationDate;
        this.applicationCategory = applicationCategory;
    }
}
