package com.example.formlab.dto;

import java.util.List;

public record DisplayBodyPartDto(
        Long id,
        String name,
        List<String> exerciseNames
) {
}
