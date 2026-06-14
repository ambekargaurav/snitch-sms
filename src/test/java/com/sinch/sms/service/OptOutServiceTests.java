package com.sinch.sms.service;

import com.sinch.sms.repository.InMemoryOptOutRepository;
import com.sinch.sms.repository.OptOutRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OptOutServiceTests {

    private final OptOutRepository repository = new InMemoryOptOutRepository();
    private final OptOutService service = new OptOutService(repository);

    @Test
    void shouldMarkNumberAsOptedOut() {
        service.optOut("+61491570156");
        assertThat(
                service.isOptedOut("+61491570156"))
                .isTrue();
    }
}
