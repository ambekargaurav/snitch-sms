package com.sinch.sms.service;

import com.sinch.sms.exception.InvalidPhoneNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Validates phone numbers against regional formats.
 *
 * Assumptions:
 * AU numbers: +61 followed by 9 digits
 * NZ numbers: +64 followed by 8-9 digits
 * Global numbers: + followed by 7-15 digits
 */
@Slf4j
@Service
public class PhoneNumberValidator {

    private static final Pattern AU = Pattern.compile("^\\+61\\d{9}$");
    private static final Pattern NZ = Pattern.compile("^\\+64\\d{8,9}$");
    private static final Pattern GLOBAL = Pattern.compile("^\\+[1-9]\\d{6,14}$");
    public void validate(String number) {
        log.debug("Validating phone number: {}", number);
        boolean valid =
                AU.matcher(number).matches()
                        || NZ.matcher(number).matches()
                        || GLOBAL.matcher(number).matches();

        if (!valid) {
            log.warn("Invalid phone number: {}", number);
            throw new InvalidPhoneNumberException(number);
        }
        log.debug("Phone number validated successfully: {}", number);
    }

}
