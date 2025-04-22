package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import lombok.Data;

@Data
public class UpdateMunicipalityRequestDTO {
    private MunicipalityDTO municipality;
}
