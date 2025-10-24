package ch.tbz.recipe.planner.service;

import org.springframework.stereotype.Service;
import ch.tbz.recipe.planner.entities.*;
import ch.tbz.recipe.planner.repository.*;
import java.util.List;

@Service
public class MealPlanService {
    private final MealPlanRepository mealPlanRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    public MealPlanEntity createWeeklyPlan(String weekStartDate) {
        MealPlanEntity plan = new MealPlanEntity();
        plan.setWeekStartDate(weekStartDate);
        return mealPlanRepository.save(plan);
    }

    public List<MealPlanEntity> getAllPlans() {
        return mealPlanRepository.findAll();
    }
}
