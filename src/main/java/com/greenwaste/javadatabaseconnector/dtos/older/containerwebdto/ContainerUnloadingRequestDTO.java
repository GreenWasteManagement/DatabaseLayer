package com.greenwaste.javadatabaseconnector.dtos.older.containerwebdto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerUnloadingRequestDTO {

    @NotNull
    private Long smasId;

    @NotNull
    private Long containerId;
}
