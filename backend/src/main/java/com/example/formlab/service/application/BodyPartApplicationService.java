package com.example.formlab.service.application;



import com.example.formlab.dto.CreateBodyPartDto;
import com.example.formlab.dto.DisplayBodyPartDto;

import java.util.List;
import java.util.Optional;

public interface BodyPartApplicationService {
    List<DisplayBodyPartDto> findAll();
    Optional<DisplayBodyPartDto> findById(Long id);
    DisplayBodyPartDto save(CreateBodyPartDto createBodyPartDto);
    Optional<DisplayBodyPartDto> deleteById(Long id);
    Optional<DisplayBodyPartDto> update(Long id, CreateBodyPartDto createBodyPartDto);
    Optional<DisplayBodyPartDto> findByName(String name);
}
