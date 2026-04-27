package com.example.formlab.dto;

import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;

import java.util.List;
import java.util.Set;

public record CreateBodyPartDto(
        String name,
        List<Exercise> exercises
) {

}
