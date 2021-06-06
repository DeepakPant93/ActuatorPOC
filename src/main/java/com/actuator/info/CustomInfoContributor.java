package com.actuator.info;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		Map<String, Integer> map = new HashMap<>();
		map.put("active", 10);
		map.put("in-active", 3);

		builder.withDetail("user", map).build();
	}
}
