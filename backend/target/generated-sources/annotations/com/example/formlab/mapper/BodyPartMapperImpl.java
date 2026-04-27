package com.example.formlab.mapper;

import com.example.formlab.dto.CreateBodyPartDto;
import com.example.formlab.dto.DisplayBodyPartDto;
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
public class BodyPartMapperImpl implements BodyPartMapper {

    @Override
    public DisplayBodyPartDto toDto(BodyPart bodyPart) {
        if ( bodyPart == null ) {
            return null;
        }

        List<String> exerciseNames = null;
        Long id = null;
        String name = null;

        exerciseNames = exerciseListToStringList( bodyPart.getExercises() );
        id = bodyPart.getId();
        name = bodyPart.getName();

        DisplayBodyPartDto displayBodyPartDto = new DisplayBodyPartDto( id, name, exerciseNames );

        return displayBodyPartDto;
    }

    @Override
    public BodyPart toEntity(CreateBodyPartDto dto) {
        if ( dto == null ) {
            return null;
        }

        BodyPart bodyPart = new BodyPart();

        bodyPart.setName( dto.name() );

        return bodyPart;
    }

    @Override
    public List<DisplayBodyPartDto> toDtoList(List<BodyPart> bodyParts) {
        if ( bodyParts == null ) {
            return null;
        }

        List<DisplayBodyPartDto> list = new ArrayList<DisplayBodyPartDto>( bodyParts.size() );
        for ( BodyPart bodyPart : bodyParts ) {
            list.add( toDto( bodyPart ) );
        }

        return list;
    }

    protected List<String> exerciseListToStringList(List<Exercise> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( Exercise exercise : list ) {
            list1.add( map( exercise ) );
        }

        return list1;
    }
}
