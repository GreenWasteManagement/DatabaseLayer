package com.greenwaste.javadatabaseconnector.dtos.user.request.notusednow;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateSmasRequestDTO {

    private Smas smas;

    @Getter
    @Setter
    @Data
    public static class Smas {
        private Long id;
        private String position;
        private String employeeCode;
        private String citizenCardCode;
    }
}

