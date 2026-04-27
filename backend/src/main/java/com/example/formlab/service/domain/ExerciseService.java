package com.example.formlab.service.domain;


import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    List<Exercise> findAll();
    Optional<Exercise> findById(Long id);
    Exercise save(Exercise exercise);
    Optional<Exercise> deleteById(Long id);
    Optional<Exercise> update(Long id, Exercise exercise);
    List<Exercise> findByBodyPart(BodyPart bodyPart);
}
