package com.actuator.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("custom")
public class CustomHealthIndicator implements HealthIndicator {

	private static final String DESCRIPTION_KEY = "description";

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	private boolean status = false;

	@Override
	public Health health() {
		status = !status;
		if (status) {
			AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
			AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.CORRECT);

			return Health.up().withDetail(DESCRIPTION_KEY, "Service is up").build();
		} else {
			AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC);
			AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.BROKEN);

			return Health.down().withDetail(DESCRIPTION_KEY, "Service is down").build();
		}
	}
}
