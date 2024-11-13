package com.ra.repository;

import com.ra.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //Tìm kiếm
    @Query("select s from Student s where s.fullName like %:keyword%")
    List<Student> searchByName(String keyword);

    //Kiểm tra tồn tại số điện thoại
    Boolean existsStudentByPhone(String phone);
}
