package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String userName);


    @Query("""
            SELECT u FROM User u
            JOIN u.admin a
            JOIN u.address ad
            JOIN ad.postalCode pc
            WHERE u.role = 'ADMIN'
            """)
    List<User> findAllWithAdminDetails();


    @Query("""
            SELECT u FROM User u
            JOIN u.smas s
            JOIN u.address ad
            JOIN ad.postalCode pc
            WHERE u.role = 'SMAS'
            """)
    List<User> findAllWithSmasDetails();


    @Query("""
            SELECT u FROM User u
            JOIN u.municipality m
            JOIN u.address ad
            JOIN ad.postalCode pc
            WHERE u.role = 'MUNICIPALITY'
            """)
    List<User> findAllWithMunicipalityDetails();

    @Query("""
            SELECT u FROM User u
            JOIN u.admin a
            JOIN u.address ad
            JOIN ad.postalCode pc
            WHERE u.role = 'ADMIN' AND u.id = :id
            """)
    Optional<User> findUserWithAdminDetailsById(@Param("id") Long id);


    @Query("""
            SELECT u FROM User u
            JOIN u.smas s
            JOIN u.address ad
            JOIN ad.postalCode pc
            WHERE u.role = 'SMAS' AND u.id = :id
            """)
    Optional<User> findUserWithSmasDetailsById(@Param("id") Long id);


    @Query("""
            SELECT u FROM User u
            JOIN u.municipality m
            JOIN u.address ad
            JOIN ad.postalCode pc
            WHERE u.role = 'MUNICIPALITY' AND u.id = :id
            """)
    Optional<User> findUserWithMunicipalityDetailsById(@Param("id") Long id);


    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'MUNICIPALITY'")
    Long countMunicipalityUsers();


    /*
    /// Admin
    @EntityGraph(attributePaths = {"user.admin", "user.address", "user.address.postalCode"})
    List<User> findAllWithAdminDetails();

    // SMAS
    @EntityGraph(attributePaths = {"user.smas", "user.address", "user.address.postalCode"})
    List<User> findAllWithSmasDetails();

    // Municipality
    @EntityGraph(attributePaths = {"user.municipality", "user.address", "user.address.postalCode"})
    List<User> findAllWithMunicipalityDetails();


    // Admin
    @EntityGraph(attributePaths = {"user.admin", "user.address", "user.address.postalCode"})
    Optional<User> findUserWithAdminDetailsById(Long id);

    // SMAS
    @EntityGraph(attributePaths = {"user.smas", "user.address", "user.address.postalCode"})
    Optional<User> findUserWithSmasDetailsById(Long id);

    // Municipality
    @EntityGraph(attributePaths = {"user.municipality", "user.address", "user.address.postalCode"})
    Optional<User> findUserWithMunicipalityDetailsById(Long id);
*/
}
