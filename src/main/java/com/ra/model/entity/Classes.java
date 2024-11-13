package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Classes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "class_name", length = 100, unique = true)
    private String ClassName;
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "classId")
    private Set<Student> students;
}
