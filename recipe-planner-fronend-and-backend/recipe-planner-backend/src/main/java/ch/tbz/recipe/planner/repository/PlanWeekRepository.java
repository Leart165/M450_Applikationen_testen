package ch.tbz.recipe.planner.repository;

import ch.tbz.recipe.planner.entities.PlanWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanWeekRepository extends JpaRepository<PlanWeek, UUID> {
    Optional<PlanWeek> findByStartDate(java.time.LocalDate startDate);
}
