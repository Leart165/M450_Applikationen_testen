package ch.tbz.recipe.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.tbz.recipe.planner.entities.MealPlanEntity;

public interface MealPlanRepository extends JpaRepository<MealPlanEntity, Long> { }
