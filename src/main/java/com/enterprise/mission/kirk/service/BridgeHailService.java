package com.enterprise.mission.kirk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BridgeHailService {
    private static final Logger log = LoggerFactory.getLogger(BridgeHailService.class);

    public String hail(String officer, Duration delay) {
        log.info("🚀 Incoming hail from officer: {}", officer);
        log.debug("Routing signal to main viewscreen with trace context");
        if (delay.isPositive()) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {    //Ignored  }
            }
        }
        return "Bridge to " + officer + ": You are cleared to dock.";
    }

}
