package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.PostalCode;
import com.greenwaste.javadatabaseconnector.repository.PostalCodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostalCodeService {

    private final PostalCodeRepository postalCodeRepository;

    public PostalCodeService(PostalCodeRepository postalCodeRepository) {
        this.postalCodeRepository = postalCodeRepository;
    }

    public List<PostalCode> getAllPostalCodes() {
        return postalCodeRepository.findAll();
    }

    public PostalCode getPostalCodeById(Long id) {
        return postalCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PostalCode not found"));
    }

    @Transactional
    public PostalCode createPostalCode(PostalCode postalCode) {
        return postalCodeRepository.save(postalCode);
    }

    @Transactional
    public PostalCode updatePostalCode(Long id, PostalCode updatedPostalCode) {
        PostalCode existing = getPostalCodeById(id);
        existing.setPostalCode(updatedPostalCode.getPostalCode());
        existing.setCounty(updatedPostalCode.getCounty());
        existing.setDistrict(updatedPostalCode.getDistrict());
        return postalCodeRepository.save(existing);
    }

    @Transactional
    public void deletePostalCode(Long id) {
        postalCodeRepository.deleteById(id);
    }


}
