package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.User;
import com.greenwaste.javadatabaseconnector.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existing = getUserById(id);
        existing.setName(updatedUser.getName());
        existing.setUsername(updatedUser.getUsername());
        existing.setPassword(updatedUser.getPassword());
        existing.setEmail(updatedUser.getEmail());
        existing.setPhoneNumber(updatedUser.getPhoneNumber());
        existing.setRole(updatedUser.getRole());
        return userRepository.save(existing);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
