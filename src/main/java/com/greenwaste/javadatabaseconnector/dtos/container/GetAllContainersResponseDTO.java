package com.greenwaste.javadatabaseconnector.dtos.container;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllContainersResponseDTO {
    private List<ContainerDTO> containers;
}
