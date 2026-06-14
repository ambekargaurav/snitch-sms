package com.sinch.sms.service;

import com.sinch.sms.exception.InvalidPhoneNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PhoneNumberValidatorTest {

    private final PhoneNumberValidator validator = new PhoneNumberValidator();

    @Test
    void shouldAcceptValidAustralianNumber() {

        assertThatCode(() ->
                validator.validate("+61491570156"))
                .doesNotThrowAnyException();
    }
    @Test
    void shouldAcceptValidNewZealandNumber() {

        assertThatCode(() ->
                validator.validate("+64211234567"))
                .doesNotThrowAnyException();
    }
    @Test
    void shouldAcceptValidGlobalNumber() {

        assertThatCode(() ->
                validator.validate("+12025550123"))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldRejectEmptyNumber() {

        assertThatThrownBy(() ->
                validator.validate(""))
                .isInstanceOf(InvalidPhoneNumberException.class);
    }

    @Test
    void shouldRejectInvalidPhoneNumber() {

        assertThatThrownBy(() ->
                validator.validate("12345"))
                .isInstanceOf(InvalidPhoneNumberException.class);
    }
}
