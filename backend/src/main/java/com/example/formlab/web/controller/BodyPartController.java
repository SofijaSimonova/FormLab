package com.example.formlab.web.controller;

import com.example.formlab.dto.CreateBodyPartDto;
import com.example.formlab.dto.DisplayBodyPartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.formlab.service.application.BodyPartApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/bodyParts")
public class BodyPartController {

    private final BodyPartApplicationService bodyPartApplicationService;

    public BodyPartController(BodyPartApplicationService bodyPartApplicationService) {
        this.bodyPartApplicationService = bodyPartApplicationService;
    }


    @GetMapping
    public ResponseEntity<List<DisplayBodyPartDto>> findAll(){
        return ResponseEntity.ok(bodyPartApplicationService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DisplayBodyPartDto> findById(@PathVariable Long id){
        return bodyPartApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisplayBodyPartDto> save(@RequestBody CreateBodyPartDto createBodyPartDto){
        return ResponseEntity.ok(bodyPartApplicationService.save(createBodyPartDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayBodyPartDto> update(@RequestBody CreateBodyPartDto  createBodyPartDto, @PathVariable Long id){
        return bodyPartApplicationService.update(id, createBodyPartDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayBodyPartDto> delete(@PathVariable Long id){
        return bodyPartApplicationService.deleteById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name")
    public ResponseEntity<DisplayBodyPartDto> findByName(@RequestParam String name){
        return bodyPartApplicationService.findByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}

