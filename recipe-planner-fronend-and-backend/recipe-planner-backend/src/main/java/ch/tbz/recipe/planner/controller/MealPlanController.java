package ch.tbz.recipe.planner.controller;

import org.springframework.web.bind.annotation.*;
import ch.tbz.recipe.planner.entities.MealPlanEntity;
import ch.tbz.recipe.planner.service.MealPlanService;
import java.util.List;

@RestController
@RequestMapping("/api/mealplans")
@CrossOrigin(origins = "http://localhost:3000")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/create")
    public MealPlanEntity createPlan(@RequestParam String weekStartDate) {
        return mealPlanService.createWeeklyPlan(weekStartDate);
    }

    @GetMapping
    public List<MealPlanEntity> getAllPlans() {
        return mealPlanService.getAllPlans();
    }
}
