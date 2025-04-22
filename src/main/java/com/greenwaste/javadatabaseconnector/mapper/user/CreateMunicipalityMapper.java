package com.greenwaste.javadatabaseconnector.mapper.user;

import com.greenwaste.javadatabaseconnector.dtos.base.AddressDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.PostalCodeDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.UserDTO;
import com.greenwaste.javadatabaseconnector.model.Address;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import com.greenwaste.javadatabaseconnector.model.PostalCode;
import com.greenwaste.javadatabaseconnector.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateMunicipalityMapper {

    @Mapping(target = "User.id", ignore = true)
    User toUser(UserDTO dto);

    @Mapping(target = "Address.id", ignore = true)
    Address toAddress(AddressDTO dto);

    @Mapping(target = "PostalCode.id", ignore = true)
    PostalCode toPostalCode(PostalCodeDTO dto);

    @Mapping(target = "Municipality.id", ignore = true)
    Municipality toMunicipality(MunicipalityDTO dto);

    MunicipalityDTO toMunicipalityDTO(Municipality entity);
}
