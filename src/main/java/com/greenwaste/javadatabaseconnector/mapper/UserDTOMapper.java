package com.greenwaste.javadatabaseconnector.mapper;

import com.greenwaste.javadatabaseconnector.dtos.base.*;
import com.greenwaste.javadatabaseconnector.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    // === ENTITY TO DTO ===

    UserDTO toUserDTO(User user);

    AddressDTO toAddressDTO(Address address);

    PostalCodeDTO toPostalCodeDTO(PostalCode postalCode);

    AdminDTO toAdminDTO(Admin admin);

    MunicipalityDTO toMunicipalityDTO(Municipality municipality);

    SmasDTO toSmasDTO(Smas smas);

    // === DTO TO ENTITY ===

    @Mapping(target = "User.id", ignore = true)
    User toUser(UserDTO dto);

    @Mapping(target = "Address.id", ignore = true)
    Address toAddress(AddressDTO dto);

    @Mapping(target = "PostalCode.id", ignore = true)
    PostalCode toPostalCode(PostalCodeDTO dto);

    @Mapping(target = "Admin.id", ignore = true)
    Admin toAdmin(AdminDTO dto);

    @Mapping(target = "Municipality.id", ignore = true)
    Municipality toMunicipality(MunicipalityDTO dto);

    @Mapping(target = "Smas.id", ignore = true)
    Smas toSmas(SmasDTO dto);
}
