package com.ra.model.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponseDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String address;
    private String avatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    private Boolean gender;
    private String class_Name;
}
