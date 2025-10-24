package ch.tbz.recipe.planner.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MealPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String weekStartDate;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealDayEntity> days = new ArrayList<>();

    public Long getId() { return id; }
    public String getWeekStartDate() { return weekStartDate; }
    public void setWeekStartDate(String weekStartDate) { this.weekStartDate = weekStartDate; }
    public List<MealDayEntity> getDays() { return days; }
    public void setDays(List<MealDayEntity> days) { this.days = days; }
}
