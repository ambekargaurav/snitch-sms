package com.sinch.sms.service;

import com.sinch.sms.entity.Carrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarrierRoutingServiceTest {

    private CarrierRoutingService router;

    @BeforeEach
    void setup() {
        router = new CarrierRoutingService();
    }

    @Test
    void shouldRouteAustrianNumberToTelstraOrOptus() {

        Carrier carrier = router.route("+61491570156");
        assertThat(carrier)
                .isIn(Carrier.TELSTRA, Carrier.OPTUS);
    }

    @Test
    void shouldRouteNewZealandNumberToSpark() {
        Carrier carrier = router.route("+64211234567");
        assertThat(carrier)
                .isIn(Carrier.SPARK);

    }

    @Test
    void shouldRouteGlobalNumberToGlobal() {
        Carrier carrier = router.route("+12025550123");
        assertThat(carrier)
                .isIn(Carrier.GLOBAL);
    }

}

