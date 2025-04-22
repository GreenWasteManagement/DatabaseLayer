package com.greenwaste.javadatabaseconnector.dtos.container.request;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateContainerRequestDTO {
    private ContainerDTO container;
}
