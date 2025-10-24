package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.entities.PlanItem;
import ch.tbz.recipe.planner.entities.PlanWeek;
import ch.tbz.recipe.planner.service.PlanerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/planer")
@CrossOrigin(origins = "*")
public class PlanerController {

    private final PlanerService planerService;

    public PlanerController(PlanerService planerService) {
        this.planerService = planerService;
    }

    @GetMapping("/{startDate}")
    public PlanWeek getWeek(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return planerService.getWeek(startDate);
    }

    @PostMapping("/{startDate}")
    public PlanWeek addItem(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestBody PlanItem item) {
        return planerService.addItem(startDate, item);
    }

    @DeleteMapping("/item/{id}")
    public PlanWeek deleteItem(@PathVariable UUID id) {
        return planerService.deleteItem(id);
    }

    @PutMapping("/item/{id}")
    public PlanWeek updateItem(@PathVariable UUID id, @RequestBody PlanItem item) {
        return planerService.updateItem(id, item);
    }
}
