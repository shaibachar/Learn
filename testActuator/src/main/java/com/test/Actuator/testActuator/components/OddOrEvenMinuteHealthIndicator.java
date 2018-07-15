package com.test.Actuator.testActuator.components;

import java.time.LocalTime;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class OddOrEvenMinuteHealthIndicator implements HealthIndicator {
	@Override
	public Health health() {
		int errorCode = 0;
		LocalTime now = LocalTime.now();
		if (now.getMinute() % 2 != 0) {
			return Health.down().withDetail("Error Code", errorCode).build();
		}
		return Health.up().build();
	}

}