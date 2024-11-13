package com.ra.service.Class;

import com.ra.model.dto.Class.ClassRequestDTO;
import com.ra.model.dto.Class.ClassResponseDTO;
import com.ra.model.dto.Class.ClassUpdateRequestDTO;
import com.ra.model.entity.Classes;
import com.ra.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImp implements ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassServiceImp(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public List<ClassResponseDTO> findAll() {
        //convert entity --> DTO
        List<Classes> classes = classRepository.findAll();
        List<ClassResponseDTO> responseDTOS = new ArrayList<>();
        for (Classes c : classes) {
            ClassResponseDTO responseDTO = new ClassResponseDTO();
            responseDTO.setId(c.getId());
            responseDTO.setClassName(c.getClassName());
            responseDTO.setStatus(c.getStatus());
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }

    @Override
    public ClassResponseDTO create(ClassRequestDTO classRequestDTO) {
        Classes c = Classes.builder()
                .ClassName(classRequestDTO.getClassName())
                .status(classRequestDTO.getStatus())
                .build();
        Classes classNew = classRepository.save(c);

        ClassResponseDTO classResponseDTO = ClassResponseDTO.builder()
                .id(classNew.getId())
                .ClassName(classNew.getClassName())
                .status(classNew.getStatus())
                .build();
        return classResponseDTO;
    }


    @Override
    public ClassResponseDTO findById(Long id) {
        Classes classes = classRepository.findById(id).orElse(null);
        //convert entity --> DTO
        if (classes != null) {
            ClassResponseDTO classResponseDTO = ClassResponseDTO.builder()
                    .id(classes.getId())
                    .ClassName(classes.getClassName())
                    .status(classes.getStatus())
                    .build();
            return classResponseDTO;
        }
        return null;
    }

    @Override
    public ClassResponseDTO update(ClassUpdateRequestDTO classUpdateRequestDTO) {
        Classes c = Classes.builder()
                .id(classUpdateRequestDTO.getId())
                .ClassName(classUpdateRequestDTO.getClassName())
                .status(classUpdateRequestDTO.getStatus())
                .build();
        Classes classNew = classRepository.save(c);

        ClassResponseDTO classResponseDTO = ClassResponseDTO.builder()
                .id(classNew.getId())
                .ClassName(classNew.getClassName())
                .status(classNew.getStatus())
                .build();
        return classResponseDTO;
    }

    @Override
    public void delete(Long id) {
        classRepository.deleteById(id);
    }
}
