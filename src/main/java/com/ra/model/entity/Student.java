package com.ra.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", length = 100)
    private String fullName;
    @Column(name = "phone", length = 100, unique = true)
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "gender")
    private Boolean gender;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Classes classId;
}
