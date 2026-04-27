package com.example.formlab.service.domain;



import com.example.formlab.model.domain.BodyPart;

import java.util.List;
import java.util.Optional;

public interface BodyPartService {
    List<BodyPart> findAll();
    Optional<BodyPart> findById(Long id);
    BodyPart save(BodyPart bodyPart);
    Optional<BodyPart> deleteById(Long id);
    Optional<BodyPart> update(Long id, BodyPart bodyPart);
    Optional<BodyPart> findByName(String name);
}
