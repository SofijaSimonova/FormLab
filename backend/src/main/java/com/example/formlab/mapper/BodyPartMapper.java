package com.example.formlab.mapper;

import com.example.formlab.dto.CreateBodyPartDto;
import com.example.formlab.dto.CreateExerciseDto;
import com.example.formlab.dto.DisplayBodyPartDto;
import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BodyPartMapper {

    @Mapping(source = "exercises", target = "exerciseNames")
    DisplayBodyPartDto toDto(BodyPart bodyPart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    BodyPart toEntity(CreateBodyPartDto dto);

    List<DisplayBodyPartDto> toDtoList(List<BodyPart> bodyParts);

    default String map(Exercise exercise) {
        return exercise.getName();
    }
}