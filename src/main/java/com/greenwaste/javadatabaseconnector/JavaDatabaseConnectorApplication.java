package com.greenwaste.javadatabaseconnector;

import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class JavaDatabaseConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDatabaseConnectorApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ApplicationContext context) {
        return args -> {
            UserService userService = context.getBean(UserService.class);
            PostalCodeService postalCodeService = context.getBean(PostalCodeService.class);
            AddressService addressService = context.getBean(AddressService.class);
            AdminService adminService = context.getBean(AdminService.class);
            SmasService SmasService = context.getBean(SmasService.class);
            MunicipalityService municipalityService = context.getBean(MunicipalityService.class);
            BucketService bucketService = context.getBean(BucketService.class);
            ContainerService containerService = context.getBean(ContainerService.class);
            BucketMunicipalityService bucketMunicipalityService = context.getBean(BucketMunicipalityService.class);
            BucketMunicipalityContainerService bucketMunicipalityContainerService = context.getBean(BucketMunicipalityContainerService.class);
            ContainerUnloadingService containerUnloadingService = context.getBean(ContainerUnloadingService.class);

            PostalCode postalCode = new PostalCode();
            postalCode.setPostalCode("12345-678");
            postalCode.setCounty("Sample County");
            postalCode.setDistrict("Sample District");
            postalCode = postalCodeService.createPostalCode(postalCode);

            User adminUser = new User();
            adminUser.setName("Alice Admin");
            adminUser.setUsername("aliceadmin");
            adminUser.setPassword("password");
            adminUser.setEmail("alice.admin@example.com");
            adminUser.setPhoneNumber("111111111");
            adminUser.setRole(User.UserRole.ADMIN);

            Address adminAddress = new Address();
            adminAddress.setFloorDetails("1st Floor");
            adminAddress.setFloorNumber(1);
            adminAddress.setDoorNumber(101);
            adminAddress.setStreet("Admin Street");
            adminAddress.setPostalCode(postalCode);
            adminAddress.setUser(adminUser);

            Admin admin = new Admin();
            admin.setCitizenCardCode("ADMIN12345");
            admin.setUser(adminUser);

            adminUser.setAddress(adminAddress);
            adminUser.setAdmin(admin);

            userService.createUser(adminUser);


            User smasUser = new User();
            smasUser.setName("Bob Smas");
            smasUser.setUsername("bobsmas");
            smasUser.setPassword("password");
            smasUser.setEmail("bob.smas@example.com");
            smasUser.setPhoneNumber("222222222");
            smasUser.setRole(User.UserRole.SMAS);

            Address smasAddress = new Address();
            smasAddress.setFloorDetails("2nd Floor");
            smasAddress.setFloorNumber(2);
            smasAddress.setDoorNumber(202);
            smasAddress.setStreet("Smas Street");
            smasAddress.setPostalCode(postalCode);
            smasAddress.setUser(smasUser);

            Smas smas = new Smas();
            smas.setEmployeeCode("EMP001");
            smas.setCitizenCardCode("SMAS12345");
            smas.setPosition("Manager");
            smas.setUser(smasUser);

            smasUser.setAddress(smasAddress);
            smasUser.setSmas(smas);

            userService.createUser(smasUser);


            User municipalityUser = new User();
            municipalityUser.setName("Charlie Municipality");
            municipalityUser.setUsername("charliemun");
            municipalityUser.setPassword("password");
            municipalityUser.setEmail("charlie.municipality@example.com");
            municipalityUser.setPhoneNumber("333333333");
            municipalityUser.setRole(User.UserRole.MUNICIPALITY);

            Address municipalityAddress = new Address();
            municipalityAddress.setFloorDetails("3rd Floor");
            municipalityAddress.setFloorNumber(3);
            municipalityAddress.setDoorNumber(303);
            municipalityAddress.setStreet("Municipality Street");
            municipalityAddress.setPostalCode(postalCode);
            municipalityAddress.setUser(municipalityUser);

            Municipality municipality = new Municipality();
            municipality.setCitizenCardCode("MUNI12345");
            municipality.setNif("123456789");
            municipality.setUser(municipalityUser);

            municipalityUser.setAddress(municipalityAddress);
            municipalityUser.setMunicipality(municipality);

            userService.createUser(municipalityUser);


            Bucket bucket = new Bucket();
            bucket.setCapacity(BigDecimal.valueOf(1000.00));
            bucket.setIsAssociated(Boolean.FALSE);
            bucket = bucketService.createBucket(bucket);

            Container container = new Container();
            container.setCapacity(BigDecimal.valueOf(500.00));
            container.setLocalization("Location A");
            container.setCurrentVolumeLevel(BigDecimal.valueOf(0.0));
            container = containerService.createContainer(container);

            BucketMunicipality bucketMunicipality = new BucketMunicipality();
            bucketMunicipality.setUser(municipality);
            bucketMunicipality.setBucket(bucket);
            bucketMunicipality.setStatus(Boolean.FALSE);
            bucketMunicipality = bucketMunicipalityService.createAssociation(bucketMunicipality);

            BucketMunicipalityContainer deposit = new BucketMunicipalityContainer();
            deposit.setAssociation(bucketMunicipality);
            deposit.setContainer(container);
            deposit.setDepositAmount(BigDecimal.valueOf(250.00));
            bucketMunicipalityContainerService.createDeposit(deposit);

            ContainerUnloading unloading = new ContainerUnloading();
            unloading.setContainer(container);
            unloading.setUser(smas);
            unloading.setUnloadedQuantity(BigDecimal.valueOf(150.00));
            containerUnloadingService.createUnloading(unloading);

            System.out.println("Dados de teste criados com sucesso!");
        };
    }
}
