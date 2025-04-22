package com.greenwaste.javadatabaseconnector.dtos.container.request;

import lombok.Data;

@Data
public class ContainerUnloadingRequestDTO {
    private Long smasId;
    private Long containerId;
}
