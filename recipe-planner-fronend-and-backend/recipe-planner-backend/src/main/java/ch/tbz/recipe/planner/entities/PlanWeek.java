package ch.tbz.recipe.planner.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanWeek {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate startDate;

    @OneToMany(mappedBy = "planWeek", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanItem> items;
}
