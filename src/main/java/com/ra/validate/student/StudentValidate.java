package com.ra.validate.student;

import com.ra.repository.StudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component: bao gồm service, controller, repository
@Component
public class StudentValidate implements ConstraintValidator<StudentUnique, String> {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentValidate(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !studentRepository.existsStudentByPhone(value);
    }
}
