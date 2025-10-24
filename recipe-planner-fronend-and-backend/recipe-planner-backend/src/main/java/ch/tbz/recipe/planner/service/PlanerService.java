package ch.tbz.recipe.planner.service;

import ch.tbz.recipe.planner.entities.PlanItem;
import ch.tbz.recipe.planner.entities.PlanWeek;
import ch.tbz.recipe.planner.repository.PlanItemRepository;
import ch.tbz.recipe.planner.repository.PlanWeekRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanerService {

    private final PlanWeekRepository planWeekRepository;
    private final PlanItemRepository planItemRepository;

    public PlanerService(PlanWeekRepository planWeekRepository, PlanItemRepository planItemRepository) {
        this.planWeekRepository = planWeekRepository;
        this.planItemRepository = planItemRepository;
    }

    public PlanWeek getWeek(LocalDate startDate) {
        return planWeekRepository.findByStartDate(startDate).orElseGet(() -> {
            PlanWeek newWeek = PlanWeek.builder().startDate(startDate).build();
            return planWeekRepository.save(newWeek);
        });
    }

    public PlanWeek addItem(LocalDate startDate, PlanItem item) {
        PlanWeek week = getWeek(startDate);
        item.setPlanWeek(week);
        planItemRepository.save(item);
        return getWeek(startDate);
    }

    public PlanWeek deleteItem(UUID id) {
        Optional<PlanItem> item = planItemRepository.findById(id);
        item.ifPresent(planItemRepository::delete);
        return item.map(pi -> getWeek(pi.getPlanWeek().getStartDate()))
                   .orElse(null);
    }

    public PlanWeek updateItem(UUID id, PlanItem newItem) {
        return planItemRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(newItem.getTitle());
                    existing.setTime(newItem.getTime());
                    existing.setNotes(newItem.getNotes());
                    existing.setDay(newItem.getDay());
                    planItemRepository.save(existing);
                    return getWeek(existing.getPlanWeek().getStartDate());
                }).orElse(null);
    }
}
