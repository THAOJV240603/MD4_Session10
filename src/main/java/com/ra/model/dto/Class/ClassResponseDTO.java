package com.ra.model.dto.Class;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClassResponseDTO {
    private Long id;
    private String ClassName;
    private Boolean status;
}
