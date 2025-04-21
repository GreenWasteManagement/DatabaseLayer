package com.greenwaste.javadatabaseconnector.dtos.older.containerwebdto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateContainerRequestDTO {
    @NotNull
    private Long id;
    private BigDecimal capacity;
    private String localization;
    private BigDecimal currentVolumeLevel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getCurrentVolumeLevel() {
        return currentVolumeLevel;
    }

    public void setCurrentVolumeLevel(BigDecimal currentVolumeLevel) {
        this.currentVolumeLevel = currentVolumeLevel;
    }
}

