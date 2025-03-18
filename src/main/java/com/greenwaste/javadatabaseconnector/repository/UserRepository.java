package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
