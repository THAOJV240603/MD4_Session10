package com.ra.controller;

import com.ra.model.dto.Class.ClassRequestDTO;
import com.ra.model.dto.Class.ClassResponseDTO;
import com.ra.model.dto.Class.ClassUpdateRequestDTO;
import com.ra.service.Class.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public ResponseEntity<?> index() {
        List<ClassResponseDTO> responseDTOS = classService.findAll();
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClassRequestDTO classRequestDTO) {
        ClassResponseDTO classResponseDTO = classService.create(classRequestDTO);
        return new ResponseEntity<>(classResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ClassResponseDTO responseDTO = classService.findById(id);
        if(responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tìm thấy thông tin lớp", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClassUpdateRequestDTO classUpdateRequestDTO) {
        classUpdateRequestDTO.setId(id);
        ClassResponseDTO responseDTO = classService.update(classUpdateRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        classService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
