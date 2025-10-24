package ch.tbz.recipe.planner.repository;

import ch.tbz.recipe.planner.entities.PlanItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanItemRepository extends JpaRepository<PlanItem, UUID> {
}
