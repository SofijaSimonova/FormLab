package com.example.formlab.mapper;


import com.example.formlab.dto.CreateExerciseDto;
import com.example.formlab.dto.DisplayExerciseDto;
import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import com.example.formlab.model.enums.Difficulty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;


@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    @Mapping(source = "difficulty", target = "difficulty")
    @Mapping(source = "bodyParts", target = "bodyPartNames")
    DisplayExerciseDto toDto(Exercise exercise);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bodyParts", ignore = true) // ќе ги сетираш во service
    @Mapping(source = "difficulty", target = "difficulty")
    Exercise toEntity(CreateExerciseDto dto);

    List<DisplayExerciseDto> toDtoList(List<Exercise> exercises);

    default String map(BodyPart bodyPart) {
        return bodyPart.getName();
    }

    default String map(Difficulty difficulty) {
        return difficulty.name();
    }
}