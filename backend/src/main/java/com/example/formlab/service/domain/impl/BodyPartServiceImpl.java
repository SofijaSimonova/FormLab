package com.example.formlab.service.domain.impl;

import com.example.formlab.model.domain.BodyPart;
import org.springframework.stereotype.Service;

import com.example.formlab.repository.BodyPartRepository;
import com.example.formlab.service.domain.BodyPartService;

import java.util.List;
import java.util.Optional;

@Service
public class BodyPartServiceImpl implements BodyPartService {

    private final BodyPartRepository bodyPartRepository;

    public BodyPartServiceImpl(BodyPartRepository bodyPartRepository) {
        this.bodyPartRepository = bodyPartRepository;
    }

    @Override
    public List<BodyPart> findAll() {
        return bodyPartRepository.findAll();
    }

    @Override
    public Optional<BodyPart> findById(Long id) {
        return bodyPartRepository.findById(id);
    }

    @Override
    public BodyPart save(BodyPart bodyPart) {
        return bodyPartRepository.save(bodyPart);
    }

    @Override
    public Optional<BodyPart> deleteById(Long id) {
        Optional<BodyPart> bodyPart = findById(id);
        bodyPartRepository.deleteById(id);
        return bodyPart;
    }

    @Override
    public Optional<BodyPart> update(Long id, BodyPart bodyPart) {
        return bodyPartRepository.findById(id).map((existingBodyPart)->{
            existingBodyPart.setName(bodyPart.getName());
//            existingBodyPart.setExercises(bodyPart.getExercises());
            return bodyPartRepository.save(existingBodyPart);
        });
    }

    @Override
    public Optional<BodyPart> findByName(String name) {
        return bodyPartRepository.findByName(name);
    }
}
