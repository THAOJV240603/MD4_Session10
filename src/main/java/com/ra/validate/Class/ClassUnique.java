//package com.ra.validate.Class;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Constraint(validatedBy = ClassValidate.class)
//@Target({ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface ClassUnique {
//    String message() default "ClassName duplicate entry";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
