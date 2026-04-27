package com.example.formlab.dto;

import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import com.example.formlab.model.enums.Difficulty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;
import java.util.Set;

public record CreateExerciseDto (
    String name,
    String description,
    String animationName,
    Difficulty difficulty,
    Set<BodyPart> bodyParts
) {

}
