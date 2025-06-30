package com.enterprise.mission.kirk.controller;

import com.enterprise.mission.kirk.service.BridgeHailService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/hail")
public class BridgeHailController {
    private final BridgeHailService bridgeHailService;

    public BridgeHailController(BridgeHailService bridgeHailService) {
        this.bridgeHailService = bridgeHailService;
    }

    @GetMapping("/{officer}")
    public String hail(
            @PathVariable String officer,
            @RequestParam(name= "delay", required= false, defaultValue = "PT0S") Duration delay) {



        return bridgeHailService.hail(officer, delay);
    }

}
