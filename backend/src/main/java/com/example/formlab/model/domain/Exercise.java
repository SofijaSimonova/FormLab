package com.example.formlab.model.domain;

import com.example.formlab.model.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "animation_name")
    private String animationName;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(
            name = "exercise_body_part",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "body_part_id")
    )
    private List<BodyPart> bodyParts;

    public Exercise(Long id, String name, String description, String animationName, Difficulty difficulty, List<BodyPart> bodyParts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.animationName = animationName;
        this.difficulty = difficulty;
        this.bodyParts = bodyParts;
    }

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }
}


