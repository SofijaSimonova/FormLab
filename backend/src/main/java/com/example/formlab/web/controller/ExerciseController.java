package com.example.formlab.web.controller;

import com.example.formlab.dto.CreateExerciseDto;
import com.example.formlab.dto.DisplayExerciseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.formlab.service.application.ExerciseApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseApplicationService exerciseApplicationService;

    public ExerciseController(ExerciseApplicationService exerciseApplicationService) {
        this.exerciseApplicationService = exerciseApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayExerciseDto>> findAll(){
        return ResponseEntity.ok(exerciseApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayExerciseDto> findById(@PathVariable Long id){
        return exerciseApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisplayExerciseDto> save(@RequestBody CreateExerciseDto createExerciseDto){
        return ResponseEntity.ok(exerciseApplicationService.save(createExerciseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayExerciseDto> update(@PathVariable Long id, @RequestBody CreateExerciseDto createExerciseDto){
        return exerciseApplicationService.update(id, createExerciseDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayExerciseDto> delete(@PathVariable Long id){
        return exerciseApplicationService.deleteById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-body-part")
    public ResponseEntity<List<DisplayExerciseDto>> findByBodyPart (@RequestParam String name){
        return ResponseEntity.ok(exerciseApplicationService.findByBodyPart(name));
    }






}
