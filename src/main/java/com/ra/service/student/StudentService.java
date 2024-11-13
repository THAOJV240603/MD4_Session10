package com.ra.service.student;

import com.ra.model.dto.student.StudentRequestDTO;
import com.ra.model.dto.student.StudentResponseDTO;
import com.ra.model.dto.student.StudentUpdateRequestDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> findAll();
    StudentResponseDTO create(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO findById(Long id);
    StudentResponseDTO update(StudentUpdateRequestDTO studentUpdateRequestDTO);
    void delete(Long id);

    //Tìm kiếm
    List<StudentResponseDTO> searchByStudentName(String keyword);
}
