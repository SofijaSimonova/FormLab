package com.example.formlab.dto;

import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.enums.Difficulty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public record DisplayExerciseDto(
        Long id,
        String name,
        String description,
        String animationName,
        String difficulty,
        List<String> bodyPartNames
) {
}
