package com.ra.model.dto.Class;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassRequestDTO {
    @NotBlank(message = "Không rỗng")
//    @ClassUnique(message = "Tên lớp đã tồn tại")
    private String ClassName;
    private Boolean status;
}
