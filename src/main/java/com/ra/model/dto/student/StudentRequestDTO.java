package com.ra.model.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.validate.student.StudentUnique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDTO {
    @NotBlank(message = "Không rỗng")
    private String fullName;
    @NotBlank(message = "Không rỗng")
    @Size(min = 10, max = 11, message = "Số điện thoại bao gồm 10, 11 chữ số")
    @StudentUnique(message = "Số điện thoại đã tồn tại")
    private String phone;
    @NotBlank(message = "Không rỗng")
    private String address;
    //@NotBlank(message = "Không rỗng")
    private MultipartFile avatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate birthday;
    private Boolean gender;
    private Long classId;
}

