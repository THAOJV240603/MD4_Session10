//package com.ra.validate.Class;
//
//import com.ra.repository.ClassRepository;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
////@Component: bao gồm service, controller, repository
//@Component
//public class ClassValidate implements ConstraintValidator<ClassUnique, String> {
//
//    private final ClassRepository classRepository;
//
//    @Autowired
//    public ClassValidate(ClassRepository classRepository) {
//        this.classRepository = classRepository;
//    }
//
//    @Override
//    public boolean isValid(String ClassName, ConstraintValidatorContext context) {
//        return !classRepository.existsClassesByClassName(ClassName);
//    }
//}
