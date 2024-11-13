package com.ra.service.Class;

import com.ra.model.dto.Class.ClassRequestDTO;
import com.ra.model.dto.Class.ClassResponseDTO;
import com.ra.model.dto.Class.ClassUpdateRequestDTO;

import java.util.List;

public interface ClassService {
    List<ClassResponseDTO> findAll();
    ClassResponseDTO create(ClassRequestDTO classRequestDTO);
    ClassResponseDTO findById(Long id);
    ClassResponseDTO update(ClassUpdateRequestDTO classUpdateRequestDTO);
    void delete(Long id);
}
