package com.example.formlab.service.application;


import com.example.formlab.dto.CreateExerciseDto;
import com.example.formlab.dto.DisplayExerciseDto;

import java.util.List;
import java.util.Optional;

public interface ExerciseApplicationService {
    List<DisplayExerciseDto> findAll();
    Optional<DisplayExerciseDto> findById(Long id);
    DisplayExerciseDto save(CreateExerciseDto createExerciseDto);
    Optional<DisplayExerciseDto> deleteById(Long id);
    Optional<DisplayExerciseDto> update(Long id, CreateExerciseDto createExerciseDto);
    List<DisplayExerciseDto> findByBodyPart(String name);
}
