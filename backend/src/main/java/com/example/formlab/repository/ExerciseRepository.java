package com.example.formlab.repository;

import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByBodyPartsContaining(BodyPart bodyPart);
}
