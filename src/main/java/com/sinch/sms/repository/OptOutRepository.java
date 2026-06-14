package com.sinch.sms.repository;

public interface OptOutRepository {
    boolean existsByPhoneNumber(String phoneNumber);
    void save(String phoneNumber);
}
