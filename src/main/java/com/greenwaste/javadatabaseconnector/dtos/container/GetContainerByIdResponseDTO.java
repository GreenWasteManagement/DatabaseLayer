package com.greenwaste.javadatabaseconnector.dtos.container;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetContainerByIdResponseDTO {
    private ContainerDTO container;
}
