package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, Long> {
}
