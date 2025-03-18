package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
