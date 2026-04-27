package com.example.formlab.repository;


import com.example.formlab.model.domain.BodyPart;
import com.example.formlab.model.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BodyPartRepository extends JpaRepository<BodyPart, Long> {
        Optional<BodyPart> findByName(String name);
}
