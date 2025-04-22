package com.greenwaste.javadatabaseconnector.dtos.olderandnotusednow.containerwebdto;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateContainerRequestDTO {
    @NotNull
    private BigDecimal capacity;
    @NotNull
    private String localization;


    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }
}

