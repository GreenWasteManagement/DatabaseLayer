package com.greenwaste.javadatabaseconnector.dtos.user.response;

import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSmasResponseDTO {
    private SmasDTO smas;
}

