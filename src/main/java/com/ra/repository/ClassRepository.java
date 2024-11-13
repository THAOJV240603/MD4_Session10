package com.ra.repository;

import com.ra.model.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Classes, Long> {
    //Kiểm tra tồn tại tên lớp
//    Boolean existsClassesByClassName(String ClassName);
}
