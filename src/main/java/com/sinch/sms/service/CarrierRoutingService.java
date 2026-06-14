package com.sinch.sms.service;

import com.sinch.sms.entity.Carrier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Routes messages to carriers based on phone number prefix.
 *
 * Assumptions: Round-robin routing for AU numbers
 * NZ numbers: Spark
 * Global numbers: Global
 */
@Slf4j
@Service
public class CarrierRoutingService {

    private final AtomicInteger auCounter = new AtomicInteger(0);
    public Carrier route(String number) {
        log.debug("Routing message for number: {}", number);

        if (number.startsWith("+61")) {
            Carrier carrier = auCounter.getAndIncrement() % 2 == 0
                    ? Carrier.TELSTRA
                    : Carrier.OPTUS;
            log.debug("Routed AU number to carrier: {}", carrier);
            return carrier;
        }

        if (number.startsWith("+64")) {
            log.debug("Routed NZ number to carrier: SPARK");
            return Carrier.SPARK;
        }

        log.debug("Routed global number to carrier: GLOBAL");
        return Carrier.GLOBAL;
    }
}
