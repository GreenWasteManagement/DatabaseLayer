package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Address;
import com.greenwaste.javadatabaseconnector.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));
    }

    @Transactional
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public Address updateAddress(Long id, Address updatedAddress) {
        Address existing = getAddressById(id);
        existing.setFloorDetails(updatedAddress.getFloorDetails());
        existing.setFloorNumber(updatedAddress.getFloorNumber());
        existing.setDoorNumber(updatedAddress.getDoorNumber());
        existing.setStreet(updatedAddress.getStreet());
        existing.setPostalCode(updatedAddress.getPostalCode());
        existing.setUser(updatedAddress.getUser());
        return addressRepository.save(existing);
    }

    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
