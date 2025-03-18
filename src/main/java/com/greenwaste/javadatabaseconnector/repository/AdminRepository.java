package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
