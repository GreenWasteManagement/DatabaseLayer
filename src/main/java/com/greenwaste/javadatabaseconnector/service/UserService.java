package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.exceptions.BadCredentialsException;
import com.greenwaste.javadatabaseconnector.service.exceptions.UsernameNotFoundException;
import com.greenwaste.javadatabaseconnector.service.repository.*;
import com.greenwaste.javadatabaseconnector.webhttp.authorization.jwtcreator.JwtService;
import com.greenwaste.javadatabaseconnector.webhttp.authorization.passwordmanager.BCryptService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final SmasRepository smasRepository;
    private final MunicipalityRepository municipalityRepository;
    private final AddressRepository addressRepository;
    private final PostalCodeRepository postalCodeRepository;
    private final BCryptService bcryptService;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, AdminRepository adminRepository, SmasRepository smasRepository, MunicipalityRepository municipalityRepository, AddressRepository addressRepository, PostalCodeRepository postalCodeRepository, BCryptService bcryptService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.smasRepository = smasRepository;
        this.municipalityRepository = municipalityRepository;
        this.addressRepository = addressRepository;
        this.postalCodeRepository = postalCodeRepository;
        this.bcryptService = bcryptService;
        this.jwtService = jwtService;
    }


    // Begin of methods Cluster of many tables
    // Missing validation of postal code being equal or create a new one if not exists.

    @Transactional(rollbackFor = Exception.class)
    public Admin createAdmin(User user, Admin admin, Address address, PostalCode postalCode) {

        System.out.println(postalCode.getCounty());

        postalCodeRepository.save(postalCode);

        // Associations Between Elements
        address.setPostalCode(postalCode);
        address.setUser(user);

        admin.setUser(user);

        user.setAddress(address);
        user.setAdmin(admin);

        // End of associations Between Elements
        userRepository.save(user);

        return user.getAdmin();

    }

    @Transactional
    public Municipality createMunicipality(User user, Municipality municipality, Address address, PostalCode postalCode) {

        postalCodeRepository.save(postalCode);

        // Associations Between Elements
        address.setPostalCode(postalCode);
        address.setUser(user);

        municipality.setUser(user);

        user.setAddress(address);
        user.setMunicipality(municipality);

        // End of associations Between Elements
        userRepository.save(user);

        return user.getMunicipality();


    }

    @Transactional
    public Smas createSmas(User user, Smas smas, Address address, PostalCode postalCode) {

        postalCodeRepository.save(postalCode);

        // Associations Between Elements
        address.setPostalCode(postalCode);
        address.setUser(user);

        smas.setUser(user);

        user.setAddress(address);
        user.setSmas(smas);

        // End of associations Between Elements
        userRepository.save(user);

        return user.getSmas();

    }

    @Transactional
    public void updateUser(User updatedUser) {

        Optional<User> userOptional = userRepository.findById(updatedUser.getId());

        if (userOptional.isPresent()) {

            User existingUser = userOptional.get();

            if (updatedUser.getName() != null && !updatedUser.getName().equals(existingUser.getName())) {
                Optional<User> userWithSameName = userRepository.findByName(updatedUser.getName());
                if (!userWithSameName.isPresent()) {
                    existingUser.setName(updatedUser.getName());
                }
            }

            if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(existingUser.getEmail())) {
                Optional<User> userWithSameEmail = userRepository.findByEmail(updatedUser.getEmail());
                if (!userWithSameEmail.isPresent()) {
                    existingUser.setEmail(updatedUser.getEmail());
                }
            }

            if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().equals(existingUser.getPhoneNumber())) {
                Optional<User> userWithSamePhone = userRepository.findByPhoneNumber(updatedUser.getPhoneNumber());
                if (!userWithSamePhone.isPresent()) {
                    existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                }
            }


            userRepository.save(existingUser);
        }
    }

    @Transactional
    public void updateUserAddress(Address updatedAddress) {
        if (updatedAddress.getId() != null) {
            Optional<Address> existingAddressOpt = addressRepository.findById(updatedAddress.getId());

            if (existingAddressOpt.isPresent()) {
                Address existingAddress = existingAddressOpt.get();

                if (updatedAddress.getStreet() != null && !updatedAddress.getStreet().equals(existingAddress.getStreet())) {
                    existingAddress.setStreet(updatedAddress.getStreet());
                }

                if (updatedAddress.getFloorDetails() != null && !updatedAddress.getFloorDetails().equals(existingAddress.getFloorDetails())) {
                    existingAddress.setFloorDetails(updatedAddress.getFloorDetails());
                }

                if (updatedAddress.getFloorNumber() != null && !updatedAddress.getFloorNumber().equals(existingAddress.getFloorNumber())) {
                    existingAddress.setFloorNumber(updatedAddress.getFloorNumber());
                }

                if (updatedAddress.getDoorNumber() != null && !updatedAddress.getDoorNumber().equals(existingAddress.getDoorNumber())) {
                    existingAddress.setDoorNumber(updatedAddress.getDoorNumber());
                }


                addressRepository.save(existingAddress);

            }
        }
    }

    @Transactional
    public void updateAdmin(Admin updatedAdmin) {
        if (updatedAdmin.getId() != null) {
            Optional<Admin> existingAdminOpt = adminRepository.findById(updatedAdmin.getId());

            if (existingAdminOpt.isPresent()) {
                Admin existingAdmin = existingAdminOpt.get();

                if (updatedAdmin.getCitizenCardCode() != null && !updatedAdmin.getCitizenCardCode().equals(existingAdmin.getCitizenCardCode())) {
                    existingAdmin.setCitizenCardCode(updatedAdmin.getCitizenCardCode());
                }

                adminRepository.save(existingAdmin);

            }
        }
    }

    @Transactional
    public void updateSmas(Smas updatedSmas) {
        if (updatedSmas.getId() != null) {
            Optional<Smas> existingSmasOpt = smasRepository.findById(updatedSmas.getId());

            if (existingSmasOpt.isPresent()) {
                Smas existingSmas = existingSmasOpt.get();

                if (updatedSmas.getCitizenCardCode() != null && !updatedSmas.getCitizenCardCode().equals(existingSmas.getCitizenCardCode())) {
                    existingSmas.setCitizenCardCode(updatedSmas.getCitizenCardCode());
                }

                if (updatedSmas.getEmployeeCode() != null && !updatedSmas.getEmployeeCode().equals(existingSmas.getEmployeeCode())) {
                    existingSmas.setEmployeeCode(updatedSmas.getEmployeeCode());
                }

                if (updatedSmas.getPosition() != null && !updatedSmas.getPosition().equals(existingSmas.getPosition())) {
                    existingSmas.setPosition(updatedSmas.getPosition());
                }

                smasRepository.save(existingSmas);

            }
        }
    }

    @Transactional
    public void updateMunicipality(Municipality updatedMunicipality) {
        if (updatedMunicipality.getId() != null) {
            Optional<Municipality> existingMunicipalityOpt = municipalityRepository.findById(updatedMunicipality.getId());

            if (existingMunicipalityOpt.isPresent()) {

                Municipality existingMunicipality = existingMunicipalityOpt.get();

                if (updatedMunicipality.getCitizenCardCode() != null && !updatedMunicipality.getCitizenCardCode().equals(existingMunicipality.getCitizenCardCode())) {
                    existingMunicipality.setCitizenCardCode(updatedMunicipality.getCitizenCardCode());
                }

                if (updatedMunicipality.getNif() != null && !updatedMunicipality.getNif().equals(existingMunicipality.getNif())) {
                    existingMunicipality.setNif(updatedMunicipality.getNif());
                }

                municipalityRepository.save(existingMunicipality);

            }
        }
    }

    @Transactional
    public void updatePostalCode(PostalCode updatedPostalCode) {
        if (updatedPostalCode.getId() != null) {
            Optional<PostalCode> existingPostalCodeOpt = postalCodeRepository.findById(updatedPostalCode.getId());

            if (existingPostalCodeOpt.isPresent()) {

                PostalCode existingPostalCode = existingPostalCodeOpt.get();

                if (updatedPostalCode.getPostalCode() != null && !updatedPostalCode.getPostalCode().equals(existingPostalCode.getPostalCode())) {
                    existingPostalCode.setPostalCode(updatedPostalCode.getPostalCode());
                }

                if (updatedPostalCode.getCounty() != null && !updatedPostalCode.getCounty().equals(existingPostalCode.getCounty())) {
                    existingPostalCode.setCounty(updatedPostalCode.getCounty());
                }

                if (updatedPostalCode.getDistrict() != null && !updatedPostalCode.getDistrict().equals(existingPostalCode.getDistrict())) {
                    existingPostalCode.setDistrict(updatedPostalCode.getDistrict());
                }

                postalCodeRepository.save(existingPostalCode);

            }
        }
    }

    // No more delete methods because of Cascade deleting
    @Transactional
    public void deleteUser(User userToDelete) {
        userRepository.deleteById(userToDelete.getId());
    }

    @Transactional
    public void deletePostalCode(PostalCode postalCodeToDelete) {
        postalCodeRepository.deleteById(postalCodeToDelete.getId());
    }


    //// Detailed Gets with all necessary Info:

    /**
     * Admin + User + Address + PostalCode
     */
    @Transactional(readOnly = true)
    public Admin getAdminById(Long id) {
        return adminRepository.findWithAllDetailsById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
    }

    /**
     * SMAS + User + Address + PostalCode
     */
    @Transactional(readOnly = true)
    public Smas getSmasById(Long id) {
        return smasRepository.findWithAllDetailsById(id).orElseThrow(() -> new EntityNotFoundException("SMAS not found with id: " + id));
    }

    /**
     * Municipality + User + Address + PostalCode
     */
    @Transactional(readOnly = true)
    public Municipality getMunicipalityById(Long id) {
        return municipalityRepository.findWithAllDetailsById(id).orElseThrow(() -> new EntityNotFoundException("Municipality not found with id: " + id));
    }


    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

        if (!bcryptService.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Senha inválida");
        }

        return jwtService.generateToken(user);
    }

}