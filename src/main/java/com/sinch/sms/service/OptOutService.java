package com.sinch.sms.service;

import com.sinch.sms.repository.OptOutRepository;
import org.springframework.stereotype.Service;

@Service
public class OptOutService {
    private final OptOutRepository repository;

    public OptOutService(OptOutRepository repository) {
        this.repository = repository;
    }

    public boolean isOptedOut(String phoneNumber) {
        return repository.existsByPhoneNumber(phoneNumber);
    }

    public void optOut(String phoneNumber) {
        repository.save(phoneNumber);
    }
}
