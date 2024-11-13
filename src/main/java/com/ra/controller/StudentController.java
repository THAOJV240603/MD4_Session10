package com.ra.controller;

import com.ra.model.dto.student.StudentRequestDTO;
import com.ra.model.dto.student.StudentResponseDTO;
import com.ra.model.dto.student.StudentUpdateRequestDTO;
import com.ra.service.student.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> index() {
        List<StudentResponseDTO> responseDTOS = studentService.findAll();
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

    //@Valid @ModelAttribute("file") MultipartFile file, @RequestBody  StudentRequestDTO studentRequestDTO
    @PostMapping
    public ResponseEntity<?> create(@Valid @ModelAttribute StudentRequestDTO studentRequestDTO) {
        //String imageURL = uploadService.uploadFileToServer(file);
        StudentResponseDTO studentResponseDTO = studentService.create(studentRequestDTO);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        StudentResponseDTO responseDTO = studentService.findById(id);
        if(responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tìm thấy thông tin học sinh", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @ModelAttribute StudentUpdateRequestDTO studentUpdateRequestDTO) {
        studentUpdateRequestDTO.setId(id);
        StudentResponseDTO responseDTO = studentService.update(studentUpdateRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Tìm kiếm
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "keyword") String keyword) {
        List<StudentResponseDTO> responseDTOS = studentService.searchByStudentName(keyword);
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }
}
