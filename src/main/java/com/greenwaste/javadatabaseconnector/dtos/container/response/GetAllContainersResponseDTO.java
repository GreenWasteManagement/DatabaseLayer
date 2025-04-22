package com.greenwaste.javadatabaseconnector.dtos.container.response;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllContainersResponseDTO {
    private List<ContainerDTO> containers;
}
