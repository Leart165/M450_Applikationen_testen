package ch.tbz.recipe.planner.entities;

import jakarta.persistence.*;

@Entity
public class MealDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlanEntity mealPlan;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    public Long getId() { return id; }
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public MealPlanEntity getMealPlan() { return mealPlan; }
    public void setMealPlan(MealPlanEntity mealPlan) { this.mealPlan = mealPlan; }
    public RecipeEntity getRecipe() { return recipe; }
    public void setRecipe(RecipeEntity recipe) { this.recipe = recipe; }
}
