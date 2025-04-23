package com.greenwaste.javadatabaseconnector.dtos.container.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ContainerUnloadingRequestDTO {
    private Long smasId;
    private Long containerId;
}
