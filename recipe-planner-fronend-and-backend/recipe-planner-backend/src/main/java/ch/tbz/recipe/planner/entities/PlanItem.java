package ch.tbz.recipe.planner.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanItem {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String time;
    private String notes;
    private int day;

    @ManyToOne
    @JoinColumn(name = "week_id")
    private PlanWeek planWeek;
}
