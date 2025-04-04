package fitnesstracker.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "trackers")
public class FitnessTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String activity;
    private int duration;
    private int calories;
    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
    private String application;

    public FitnessTracker(String username,
                          String activity,
                          int duration,
                          int calories,
                          LocalDateTime uploadDate,
                          String application) {
        this.username = username;
        this.activity = activity;
        this.duration = duration;
        this.calories = calories;
        this.uploadDate = uploadDate;
        this.application = application;
    }
}
