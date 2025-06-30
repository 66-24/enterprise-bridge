package com.enterprise.mission.kirk;

import com.enterprise.mission.kirk.service.BridgeHailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class EnterpriseBridgeApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(EnterpriseBridgeApplication.class, args);
		BridgeHailService bridgeHailService = ctx.getBean(BridgeHailService.class);
		bridgeHailService.hail("Commander Uhura", Duration.ofSeconds(5));
	}

}