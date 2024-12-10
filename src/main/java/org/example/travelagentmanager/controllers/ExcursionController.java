package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.ExcursionProgram;
import org.example.travelagentmanager.repository.ExcursionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author batal
 * @Date 04.12.2024
 */
@RestController
@RequestMapping("/api/excursions")
public class ExcursionController {
    private final ExcursionRepository excursionRepository;

    public ExcursionController(ExcursionRepository excursionRepository) {
        this.excursionRepository = excursionRepository;
    }

    // Получение всех экскурсий
    @GetMapping
    public List<ExcursionProgram> getAllExcursions() {
        return excursionRepository.getAll();
    }

    // Получение экскурсии по ID
    @GetMapping("/{id}")
    public ResponseEntity<ExcursionProgram> getExcursionById(@PathVariable Long id) {
        try {
            ExcursionProgram excursion = excursionRepository.getById(id);
            return ResponseEntity.ok(excursion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Создание новой экскурсии
    @PostMapping
    public ResponseEntity<String> createExcursion(@RequestBody ExcursionProgram excursion) {
        int rowsInserted = excursionRepository.create(excursion);
        if (rowsInserted > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Excursion created successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating excursion.");
    }

    // Обновление экскурсии
    @PutMapping("/{id}")
    public ResponseEntity<String> updateExcursion(@PathVariable Long id, @RequestBody ExcursionProgram excursion) {
        int rowsUpdated = excursionRepository.update(id, excursion);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Excursion updated successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Excursion not found.");
    }

    // Удаление экскурсии
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExcursion(@PathVariable Long id) {
        int rowsDeleted = excursionRepository.delete(id);
        if (rowsDeleted > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Excursion not found.");
    }
}
