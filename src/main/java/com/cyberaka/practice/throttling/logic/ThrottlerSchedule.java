package com.cyberaka.practice.throttling.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class will invoke the throttler logic to determine TPS.
 */
@Component
public class ThrottlerSchedule {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    private Throttler throttler;

    @Scheduled(fixedRate = 1000)
    public void scheduledCall() {
        log.info("Throttling Status = " + throttler);
        throttler.checkTps();
    }

    public void setThrottler(Throttler throttler) {
        this.throttler = throttler;
    }
}
