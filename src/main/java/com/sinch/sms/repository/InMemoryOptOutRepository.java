package com.sinch.sms.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class InMemoryOptOutRepository implements OptOutRepository{
    private final Set<String> optedOutNumbers = new HashSet<>();

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return optedOutNumbers.contains(phoneNumber);
    }

    @Override
    public void save(String phoneNumber) {
        optedOutNumbers.add(phoneNumber);
    }
}
