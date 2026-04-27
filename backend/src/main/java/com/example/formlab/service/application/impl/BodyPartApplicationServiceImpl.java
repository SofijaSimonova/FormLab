package com.example.formlab.service.application.impl;



import com.example.formlab.dto.CreateBodyPartDto;
import com.example.formlab.dto.DisplayBodyPartDto;
import com.example.formlab.mapper.BodyPartMapper;
import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.service.application.BodyPartApplicationService;
import com.example.formlab.service.domain.BodyPartService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BodyPartApplicationServiceImpl implements BodyPartApplicationService {

    private final BodyPartMapper mapper;
    private final BodyPartService bodyPartService;

    public BodyPartApplicationServiceImpl(BodyPartMapper mapper, BodyPartService bodyPartService) {
        this.mapper = mapper;
        this.bodyPartService = bodyPartService;
    }

    @Override
    public List<DisplayBodyPartDto> findAll() {
        return mapper.toDtoList(bodyPartService.findAll());
    }

    @Override
    public Optional<DisplayBodyPartDto> findById(Long id) {
        return bodyPartService.findById(id).map(mapper::toDto);
    }

    @Override
    public DisplayBodyPartDto save(CreateBodyPartDto createBodyPartDto) {
        BodyPart bodyPart = mapper.toEntity(createBodyPartDto);
        return mapper.toDto(bodyPartService.save(bodyPart));
    }

    @Override
    public Optional<DisplayBodyPartDto> deleteById(Long id) {
        return bodyPartService.findById(id).map(e->{
            bodyPartService.deleteById(id);
            return mapper.toDto(e);
        });
    }

    @Override
    public Optional<DisplayBodyPartDto> update(Long id, CreateBodyPartDto createBodyPartDto) {
        BodyPart bodyPart = mapper.toEntity(createBodyPartDto);
        return bodyPartService.update(id, bodyPart)
                .map(mapper::toDto);
    }

    @Override
    public Optional<DisplayBodyPartDto> findByName(String name) {
        return bodyPartService.findByName(name).map(mapper::toDto);
    }
}
