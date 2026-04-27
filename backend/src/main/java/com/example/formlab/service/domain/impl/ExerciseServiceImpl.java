package com.example.formlab.service.domain.impl;

import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import org.springframework.stereotype.Service;

import com.example.formlab.repository.ExerciseRepository;
import com.example.formlab.service.domain.ExerciseService;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Optional<Exercise> deleteById(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exerciseRepository.deleteById(id);
        return exercise;
    }

    @Override
    public Optional<Exercise> update(Long id, Exercise exercise) {
        return exerciseRepository.findById(id).map((existingExercise) -> {
            existingExercise.setName(exercise.getName());
            existingExercise.setDescription(exercise.getDescription());
            existingExercise.setAnimationName(exercise.getAnimationName());
            existingExercise.setDifficulty(exercise.getDifficulty());
            existingExercise.setBodyParts(exercise.getBodyParts());
            return exerciseRepository.save(existingExercise);
        });

    }

    @Override
    public List<Exercise> findByBodyPart(BodyPart bodyPart) {
        return exerciseRepository.findByBodyPartsContaining(bodyPart);
    }
}
