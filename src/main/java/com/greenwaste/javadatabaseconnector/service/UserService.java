package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.dtos.user.request.UpdateAdminRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.request.UpdateMunicipalityRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.request.UpdateSmasRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.response.CountMunicipalityUsersResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.response.GetAllMunicipalitiesAndBucketsResponseDTO;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.exceptions.BadCredentialsException;
import com.greenwaste.javadatabaseconnector.service.exceptions.UsernameNotFoundException;
import com.greenwaste.javadatabaseconnector.service.repository.*;
import com.greenwaste.javadatabaseconnector.webhttp.authorization.jwtcreator.JwtService;
import com.greenwaste.javadatabaseconnector.webhttp.authorization.passwordmanager.BCryptService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private final BCryptService bCryptService;
    private final BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository;

    public UserService(UserRepository userRepository, AdminRepository adminRepository, SmasRepository smasRepository, MunicipalityRepository municipalityRepository, AddressRepository addressRepository, PostalCodeRepository postalCodeRepository, BCryptService bcryptService, JwtService jwtService, BCryptService bCryptService, BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.smasRepository = smasRepository;
        this.municipalityRepository = municipalityRepository;
        this.addressRepository = addressRepository;
        this.postalCodeRepository = postalCodeRepository;
        this.bcryptService = bcryptService;
        this.jwtService = jwtService;
        this.bCryptService = bCryptService;
        this.bucketMunicipalityContainerRepository = bucketMunicipalityContainerRepository;
    }


    // Begin of methods Cluster of many tables
    // Missing validation of postal code being equal or create a new one if not exists.

    @Transactional(rollbackFor = Exception.class)
    public Admin createAdmin(User user, Admin admin, Address address, PostalCode postalCode) {

        user.setPassword(bCryptService.encode(user.getPassword()));

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

        user.setPassword(bCryptService.encode(user.getPassword()));

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

        user.setPassword(bCryptService.encode(user.getPassword()));

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

    // Acting as One

    @Transactional
    public void updateFullAdmin(UpdateAdminRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        if (dto.getUser() != null) {
            User user = modelMapper.map(dto.getUser(), User.class);
            updateUser(user);
        }

        if (dto.getAddress() != null) {
            Address address = modelMapper.map(dto.getAddress(), Address.class);
            updateUserAddress(address);
        }

        if (dto.getAdmin() != null) {
            Admin admin = modelMapper.map(dto.getAdmin(), Admin.class);
            updateAdmin(admin);
        }


        if (dto.getPostalCode() != null) {
            PostalCode postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);
            updatePostalCode(postalCode);
        }
    }

    @Transactional
    public void updateFullSmas(UpdateSmasRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        if (dto.getUser() != null) {
            User user = modelMapper.map(dto.getUser(), User.class);
            updateUser(user);
        }

        if (dto.getAddress() != null) {
            Address address = modelMapper.map(dto.getAddress(), Address.class);
            updateUserAddress(address);
        }


        if (dto.getSmas() != null) {
            Smas smas = modelMapper.map(dto.getSmas(), Smas.class);
            updateSmas(smas);
        }


        if (dto.getPostalCode() != null) {
            PostalCode postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);
            updatePostalCode(postalCode);
        }
    }

    @Transactional
    public void updateFullMunicipality(UpdateMunicipalityRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        if (dto.getUser() != null) {
            User user = modelMapper.map(dto.getUser(), User.class);
            updateUser(user);
        }

        if (dto.getAddress() != null) {
            Address address = modelMapper.map(dto.getAddress(), Address.class);
            updateUserAddress(address);
        }

        if (dto.getMunicipality() != null) {
            Municipality municipality = modelMapper.map(dto.getMunicipality(), Municipality.class);
            updateMunicipality(municipality);
        }

        if (dto.getPostalCode() != null) {
            PostalCode postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);
            updatePostalCode(postalCode);
        }
    }


    @Transactional
    public void updateUser(User updatedUser) {
        Optional<User> userOptional = userRepository.findById(updatedUser.getId());

        if (userOptional.isEmpty()) {
            return;
        }

        User existingUser = userOptional.get();

        userRepository.findByName(updatedUser.getName()).filter(u -> !u.getId().equals(existingUser.getId())).ifPresentOrElse(u -> {
        }, () -> existingUser.setName(updatedUser.getName()));

        if (updatedUser.getUsername() != null && !updatedUser.getUsername().equals(existingUser.getUsername())) {
            userRepository.findByName(updatedUser.getUsername()).filter(u -> !u.getId().equals(existingUser.getId())).ifPresentOrElse(u -> {
            }, () -> existingUser.setName(updatedUser.getUsername()));
        }

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(existingUser.getEmail())) {
            userRepository.findByEmail(updatedUser.getEmail()).filter(u -> !u.getId().equals(existingUser.getId())).ifPresentOrElse(u -> {
            }, () -> existingUser.setEmail(updatedUser.getEmail()));
        }

        if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().equals(existingUser.getPhoneNumber())) {
            userRepository.findByPhoneNumber(updatedUser.getPhoneNumber()).filter(u -> !u.getId().equals(existingUser.getId())).ifPresentOrElse(u -> {
            }, () -> existingUser.setPhoneNumber(updatedUser.getPhoneNumber()));
        }

        userRepository.save(existingUser);
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
        if (updatedSmas.getId() == null) {
            throw new IllegalArgumentException("The SMAS ID cannot be null.");
        }

        smasRepository.findById(updatedSmas.getId()).ifPresentOrElse(existingSmas -> {

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

        }, () -> {
            throw new IllegalArgumentException("SMAS with the provided ID not found.");
        });
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

    // End Of acting as One


    // No more delete methods because of Cascade deleting
    @Transactional
    public void deleteUser(User userToDelete) {
        userRepository.deleteById(userToDelete.getId());
    }

    @Transactional
    public void deletePostalCode(PostalCode postalCodeToDelete) {
        postalCodeRepository.deleteById(postalCodeToDelete.getId());
    }


    /**
     * Admin + User + Address + PostalCode
     */
    @Transactional(readOnly = true)
    public User getAdminById(Long id) {
        return userRepository.findUserWithAdminDetailsById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
    }

    /**
     * SMAS + User + Address + PostalCode
     */
    @Transactional(readOnly = true)
    public User getSmasById(Long id) {
        return userRepository.findUserWithSmasDetailsById(id).orElseThrow(() -> new EntityNotFoundException("SMAS not found with id: " + id));
    }

    /**
     * Municipality + User + Address + PostalCode
     */
    @Transactional(readOnly = true)
    public User getMunicipalityById(Long id) {
        return userRepository.findUserWithMunicipalityDetailsById(id).orElseThrow(() -> new EntityNotFoundException("Municipality not found with id: " + id));
    }

    /**
     * Admin + User + Address + PostalCode (all)
     */
    @Transactional(readOnly = true)
    public List<User> getAllAdmins() {
        List<User> users = userRepository.findAllWithAdminDetails();
        users.forEach(user -> System.out.println("Fetched Admin: " + user));
        return users;
    }

    /**
     * SMAS + User + Address + PostalCode (all)
     */
    @Transactional(readOnly = true)
    public List<User> getAllSmas() {
        List<User> users = userRepository.findAllWithSmasDetails();
        users.forEach(user -> System.out.println("Fetched SMAS: " + user));
        return users;
    }

    /**
     * Municipality + User + Address + PostalCode (all)
     */
    @Transactional(readOnly = true)
    public List<User> getAllMunicipalities() {
        List<User> users = userRepository.findAllWithMunicipalityDetails();
        users.forEach(user -> System.out.println("Fetched Municipality: " + user));
        return users;
    }

    public GetAllMunicipalitiesAndBucketsResponseDTO getAllMunicipalitiesWithActiveBuckets() {
        ModelMapper modelMapper = new ModelMapper();

        List<BucketMunicipalityContainer> containers = bucketMunicipalityContainerRepository.findAllActiveWithRelations();

        Map<Long, GetAllMunicipalitiesAndBucketsResponseDTO.MunicipalityData> municipalityMap = new HashMap<>();

        for (BucketMunicipalityContainer container : containers) {
            BucketMunicipality association = container.getAssociation();
            Municipality municipality = association.getUser().getUser().getMunicipality();
            Long municipalityId = municipality.getId();

            GetAllMunicipalitiesAndBucketsResponseDTO.MunicipalityData data = municipalityMap.computeIfAbsent(municipalityId, id -> {
                GetAllMunicipalitiesAndBucketsResponseDTO.MunicipalityData dto = new GetAllMunicipalitiesAndBucketsResponseDTO.MunicipalityData();
                dto.setUser(modelMapper.map(municipality.getUser(), GetAllMunicipalitiesAndBucketsResponseDTO.User.class));
                dto.setMunicipality(modelMapper.map(municipality, GetAllMunicipalitiesAndBucketsResponseDTO.Municipality.class));
                dto.setAddress(modelMapper.map(municipality.getUser().getAddress(), GetAllMunicipalitiesAndBucketsResponseDTO.Address.class));
                dto.setPostalCode(modelMapper.map(municipality.getUser().getAddress().getPostalCode(), GetAllMunicipalitiesAndBucketsResponseDTO.PostalCode.class));
                dto.setBuckets(new ArrayList<>());
                return dto;
            });

            GetAllMunicipalitiesAndBucketsResponseDTO.Bucket bucketDTO = new GetAllMunicipalitiesAndBucketsResponseDTO.Bucket();
            bucketDTO.setId(association.getBucket().getId());
            bucketDTO.setCapacity(association.getBucket().getCapacity());
            bucketDTO.setStatus(association.getBucket().getIsAssociated());

            if (!data.getBuckets().stream().anyMatch(b -> b.getId().equals(bucketDTO.getId()))) {
                data.getBuckets().add(bucketDTO);
            }
        }

        GetAllMunicipalitiesAndBucketsResponseDTO response = new GetAllMunicipalitiesAndBucketsResponseDTO();
        response.setMunicipalities(new ArrayList<>(municipalityMap.values()));
        return response;
    }

    public CountMunicipalityUsersResponseDTO getCountMunicipalityUsers() {
        long count = userRepository.countMunicipalityUsers();
        CountMunicipalityUsersResponseDTO response = new CountMunicipalityUsersResponseDTO();
        response.setCount(count);
        return response;
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

        if (!bcryptService.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Senha inválida");
        }

        return jwtService.generateToken(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
    }
}