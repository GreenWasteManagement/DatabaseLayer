package com.greenwaste.javadatabaseconnector.databasepopulator;

import com.greenwaste.javadatabaseconnector.databaseseed.DatabaseSeed;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.BucketService;
import com.greenwaste.javadatabaseconnector.service.ContainerService;
import com.greenwaste.javadatabaseconnector.service.UserService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabasePopulator {


    private final UserService userService;
    private final BucketService bucketService;
    private final ContainerService containerService;
    private final DatabaseSeed databaseSeed;

    public DatabasePopulator(UserService userService,
                             BucketService bucketService,
                             ContainerService containerService,
                             DatabaseSeed databaseSeed) {
        this.userService = userService;
        this.bucketService = bucketService;
        this.containerService = containerService;
        this.databaseSeed = databaseSeed;
    }

    public void loadMockData() {


        //databaseSeed.resetDatabase();
        //databaseSeed.createDatabase();

        PostalCode postalCode1 = new PostalCode();
        postalCode1.setPostalCode("99999-678");
        postalCode1.setCounty("Sample County");
        postalCode1.setDistrict("Sample District");


        PostalCode postalCode2 = new PostalCode();
        postalCode2.setPostalCode("22346-678");
        postalCode2.setCounty("Sample County2");
        postalCode2.setDistrict("Sample District2");


        PostalCode postalCode3 = new PostalCode();
        postalCode3.setPostalCode("12346-679");
        postalCode3.setCounty("Sample County3");
        postalCode3.setDistrict("Sample District3");


        User adminUser = new User();
        adminUser.setName("Alice Admin");
        adminUser.setUsername("aliceadmin");
        adminUser.setPassword("password");
        adminUser.setEmail("alice.admin@example.com");
        adminUser.setPhoneNumber("111111111");
        adminUser.setRole(User.user_role.ADMIN);

        Address adminAddress = new Address();
        adminAddress.setFloorDetails("1st Floor");
        adminAddress.setFloorNumber(1);
        adminAddress.setDoorNumber(101);
        adminAddress.setStreet("Admin Street");
        adminAddress.setPostalCode(postalCode1);

        Admin admin = new Admin();
        admin.setCitizenCardCode("ADMIN12345");


        Admin theAdminUser = userService.createAdmin(adminUser, admin, adminAddress, postalCode1);


        User smasUser = new User();
        smasUser.setName("Bob Smas");
        smasUser.setUsername("bobsmas");
        smasUser.setPassword("password");
        smasUser.setEmail("bob.smas@example.com");
        smasUser.setPhoneNumber("222222222");
        smasUser.setRole(User.user_role.SMAS);

        Address smasAddress = new Address();
        smasAddress.setFloorDetails("2nd Floor");
        smasAddress.setFloorNumber(2);
        smasAddress.setDoorNumber(202);
        smasAddress.setStreet("Smas Street");

        Smas smas = new Smas();
        smas.setEmployeeCode("EMP001");
        smas.setCitizenCardCode("SMAS12345");
        smas.setPosition("Manager");


        Smas theSmasUser = userService.createSmas(smasUser, smas, smasAddress, postalCode2);

        theSmasUser.setCitizenCardCode("SMAS9999999999999999999999");

        userService.updateSmas(theSmasUser);

        User municipalityUser = new User();
        municipalityUser.setName("Charlie Municipality");
        municipalityUser.setUsername("charliemun");
        municipalityUser.setPassword("password");
        municipalityUser.setEmail("charlie.municipality@example.com");
        municipalityUser.setPhoneNumber("333333333");
        municipalityUser.setRole(User.user_role.MUNICIPALITY);

        Address municipalityAddress = new Address();
        municipalityAddress.setFloorDetails("3rd Floor");
        municipalityAddress.setFloorNumber(3);
        municipalityAddress.setDoorNumber(303);
        municipalityAddress.setStreet("Municipality Street");

        Municipality municipality = new Municipality();
        municipality.setCitizenCardCode("MUNI12345");
        municipality.setNif("123456789");


        Municipality theMunicipality = userService.createMunicipality(municipalityUser, municipality, municipalityAddress, postalCode3);


        Bucket bucket = new Bucket();
        bucket.setCapacity(BigDecimal.valueOf(100.00));
        bucket.setIsAssociated(Boolean.FALSE);


        Bucket theBucket = bucketService.createBucket(bucket);


        Container container = new Container();
        container.setCapacity(BigDecimal.valueOf(1500.00));
        container.setLocalization("Location A");
        container.setCurrentVolumeLevel(BigDecimal.valueOf(0.0));


        Container theContainer = containerService.createContainer(container);


        bucketService.createBucketAssociation(theBucket.getId(), theMunicipality.getId());


        bucketService.createDeposit(municipality, theContainer.getId(), BigDecimal.valueOf(20));
        bucketService.createDeposit(municipality, theContainer.getId(), BigDecimal.valueOf(30));
        bucketService.createDeposit(municipality, theContainer.getId(), BigDecimal.valueOf(50));
        bucketService.createDeposit(municipality, theContainer.getId(), BigDecimal.valueOf(100));
        bucketService.createDeposit(municipality, theContainer.getId(), BigDecimal.valueOf(50));

        containerService.containerUnloading(smas.getId(), theContainer.getId());

        System.out.println("Mock Data Testes are successfully completed !");
    }
}
