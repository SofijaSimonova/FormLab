package com.example.formlab.mapper;

import com.example.formlab.dto.CreateExerciseDto;
import com.example.formlab.dto.DisplayExerciseDto;
import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T19:01:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class ExerciseMapperImpl implements ExerciseMapper {

    @Override
    public DisplayExerciseDto toDto(Exercise exercise) {
        if ( exercise == null ) {
            return null;
        }

        String difficulty = null;
        List<String> bodyPartNames = null;
        Long id = null;
        String name = null;
        String description = null;
        String animationName = null;

        difficulty = map( exercise.getDifficulty() );
        bodyPartNames = bodyPartListToStringList( exercise.getBodyParts() );
        id = exercise.getId();
        name = exercise.getName();
        description = exercise.getDescription();
        animationName = exercise.getAnimationName();

        DisplayExerciseDto displayExerciseDto = new DisplayExerciseDto( id, name, description, animationName, difficulty, bodyPartNames );

        return displayExerciseDto;
    }

    @Override
    public Exercise toEntity(CreateExerciseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Exercise exercise = new Exercise();

        exercise.setDifficulty( dto.difficulty() );
        exercise.setName( dto.name() );
        exercise.setDescription( dto.description() );
        exercise.setAnimationName( dto.animationName() );

        return exercise;
    }

    @Override
    public List<DisplayExerciseDto> toDtoList(List<Exercise> exercises) {
        if ( exercises == null ) {
            return null;
        }

        List<DisplayExerciseDto> list = new ArrayList<DisplayExerciseDto>( exercises.size() );
        for ( Exercise exercise : exercises ) {
            list.add( toDto( exercise ) );
        }

        return list;
    }

    protected List<String> bodyPartListToStringList(List<BodyPart> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( BodyPart bodyPart : list ) {
            list1.add( map( bodyPart ) );
        }

        return list1;
    }
}
