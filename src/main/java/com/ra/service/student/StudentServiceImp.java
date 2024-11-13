package com.ra.service.student;

import com.ra.UploadFile.UploadService;
import com.ra.model.dto.student.StudentRequestDTO;
import com.ra.model.dto.student.StudentResponseDTO;
import com.ra.model.dto.student.StudentUpdateRequestDTO;
import com.ra.model.entity.Classes;
import com.ra.model.entity.Student;
import com.ra.repository.ClassRepository;
import com.ra.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final UploadService uploadService;

    @Autowired
    public StudentServiceImp(ClassRepository classRepository, StudentRepository studentRepository, UploadService uploadService) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.uploadService = uploadService;
    }

    @Override
    public List<StudentResponseDTO> findAll() {
        //convert entity --> DTO
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> responseDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentResponseDTO responseDTO = new StudentResponseDTO();
            responseDTO.setId(student.getId());
            responseDTO.setFullName(student.getFullName());
            responseDTO.setPhone(student.getPhone());
            responseDTO.setAddress(student.getAddress());
            responseDTO.setAvatar(student.getAvatar());
            responseDTO.setBirthday(student.getBirthday());
            responseDTO.setGender(student.getGender());
            responseDTO.setClass_Name(student.getClassId().getClassName());
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }

    @Override
    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {
        Classes c = classRepository.findById(studentRequestDTO.getClassId()).orElse(null);
        String imageURL = uploadService.uploadFileToServer(studentRequestDTO.getAvatar());
        Student student = Student.builder()
                .fullName(studentRequestDTO.getFullName())
                .phone(studentRequestDTO.getPhone())
                .address(studentRequestDTO.getAddress())
                .avatar(imageURL)
                .birthday(studentRequestDTO.getBirthday())
                .gender(studentRequestDTO.getGender())
                .classId(c)
                .build();
        Student studentNew = studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = StudentResponseDTO.builder()
                .id(studentNew.getId())
                .fullName(studentNew.getFullName())
                .phone(studentNew.getPhone())
                .address(studentNew.getAddress())
                .avatar(studentNew.getAvatar())
                .birthday(studentNew.getBirthday())
                .gender(studentNew.getGender())
                .class_Name(studentNew.getClassId().getClassName())
                .build();
        return studentResponseDTO;
    }

    @Override
    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        //convert entity --> DTO
        if (student != null) {
            StudentResponseDTO studentResponseDTO = StudentResponseDTO.builder()
                    .id(student.getId())
                    .fullName(student.getFullName())
                    .phone(student.getPhone())
                    .address(student.getAddress())
                    .avatar(student.getAvatar())
                    .birthday(student.getBirthday())
                    .gender(student.getGender())
                    .class_Name(student.getClassId().getClassName())
                    .build();
            return studentResponseDTO;
        }
        return null;
    }

    @Override
    public StudentResponseDTO update(StudentUpdateRequestDTO studentUpdateRequestDTO) {
        Student student = studentRepository.findById(studentUpdateRequestDTO.getId()).orElse(null);
        Classes classes = classRepository.findById(studentUpdateRequestDTO.getClassId()).orElse(null);

        String imageURL = null;
        //Kiểm tra xem đã có file ảnh chưa
        if (student != null) {
            imageURL = student.getAvatar();
        }
        if (student.getAvatar() != null)
            imageURL = uploadService.uploadFileToServer(studentUpdateRequestDTO.getAvatar());

        student = Student.builder()
                .id(studentUpdateRequestDTO.getId())
                .fullName(studentUpdateRequestDTO.getFullName())
                .phone(studentUpdateRequestDTO.getPhone())
                .address(studentUpdateRequestDTO.getAddress())
                .avatar(imageURL)
                .birthday(studentUpdateRequestDTO.getBirthday())
                .gender(studentUpdateRequestDTO.getGender())
                .classId(classes)
                .build();
        Student studentNew = studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = StudentResponseDTO.builder()
                .id(studentNew.getId())
                .fullName(studentNew.getFullName())
                .phone(studentNew.getPhone())
                .address(studentNew.getAddress())
                .avatar(studentNew.getAvatar())
                .birthday(studentNew.getBirthday())
                .gender(studentNew.getGender())
                .class_Name(studentNew.getClassId().getClassName())
                .build();
        return studentResponseDTO;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    //Tìm kếm
    @Override
    public List<StudentResponseDTO> searchByStudentName(String keyword) {
        List<Student> students = studentRepository.searchByName(keyword);
        //Java 8
        List<StudentResponseDTO> responseDTOS = students.stream().map(student -> {
            StudentResponseDTO responseDTO = new StudentResponseDTO();
            responseDTO.setId(student.getId());
            responseDTO.setFullName(student.getFullName());
            responseDTO.setPhone(student.getPhone());
            responseDTO.setAddress(student.getAddress());
            responseDTO.setAvatar(student.getAvatar());
            responseDTO.setBirthday(student.getBirthday());
            responseDTO.setGender(student.getGender());
            responseDTO.setClass_Name(student.getClassId().getClassName());
            return responseDTO;
        }).toList();
        return responseDTOS;
    }
}
