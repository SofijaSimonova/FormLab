package com.example.formlab.service.application.impl;


import com.example.formlab.dto.CreateExerciseDto;
import com.example.formlab.dto.DisplayExerciseDto;
import com.example.formlab.mapper.ExerciseMapper;
import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import com.example.formlab.service.application.ExerciseApplicationService;
import com.example.formlab.service.domain.BodyPartService;
import com.example.formlab.service.domain.ExerciseService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExerciseApplicationServiceImpl implements ExerciseApplicationService {

    private final com.example.formlab.service.domain.ExerciseService exerciseService;
    private final com.example.formlab.service.domain.BodyPartService bodyPartService;
    private final ExerciseMapper mapper;

    public ExerciseApplicationServiceImpl(ExerciseService exerciseService, BodyPartService bodyPartService, ExerciseMapper mapper) {
        this.exerciseService = exerciseService;
        this.bodyPartService = bodyPartService;
        this.mapper = mapper;
    }

    @Override
    public List<DisplayExerciseDto> findAll() {
        return mapper.toDtoList(exerciseService.findAll());
    }

    @Override
    public Optional<DisplayExerciseDto> findById(Long id) {
        return exerciseService.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public DisplayExerciseDto save(CreateExerciseDto createExerciseDto) {
        Exercise exercise = mapper.toEntity(createExerciseDto);
        List<BodyPart> bodyParts = createExerciseDto.bodyParts().stream()
                .map(i -> bodyPartService.findById(i.getId())
                        .orElseThrow(() -> new RuntimeException("BodyPart not found")))
                .toList();

        exercise.setBodyParts(bodyParts);
        return mapper.toDto(exerciseService.save(exercise));
    }

    @Override
    public Optional<DisplayExerciseDto> deleteById(Long id) {
        return exerciseService.findById(id)
                .map(e -> {
                    exerciseService.deleteById(id);
                    return mapper.toDto(e);
                });
    }

    @Override
    public Optional<DisplayExerciseDto> update(Long id, CreateExerciseDto createExerciseDto) {
        List<BodyPart> bodyParts = createExerciseDto.bodyParts().stream()
                .map(i -> bodyPartService.findById(i.getId())
                        .orElseThrow(() -> new RuntimeException("BodyPart not found")))
                .toList();

        Exercise exercise = mapper.toEntity(createExerciseDto);
        exercise.setBodyParts(bodyParts); //???
        return exerciseService.update(id, exercise).map(mapper::toDto);

    }

    @Override
    public List<DisplayExerciseDto> findByBodyPart(String name) {
        BodyPart bodyPart = bodyPartService.findByName(name).orElseThrow();

        return exerciseService.findByBodyPart(bodyPart).stream().map(mapper::toDto).toList();
    }
}
