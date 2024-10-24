package com.wsu.workorderproservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionApplicationDTO {

    @NotBlank
    private Long adopterId;

    //@NotBlank
    //private Long petId;

    //private Long staffId;

    @NotBlank
    private String status;

    // Getters and Setters
}

