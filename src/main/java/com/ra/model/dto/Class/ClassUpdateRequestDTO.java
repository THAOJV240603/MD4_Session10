package com.ra.model.dto.Class;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassUpdateRequestDTO {
    private Long id;
    private String ClassName;
    private Boolean status;
}
