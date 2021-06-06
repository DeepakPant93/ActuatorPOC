package com.actuator.endpoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "release-notes", enableByDefault = true)
public class ReleaseNotesEndpoint {

	private Map<String, List<String>> releaseNotes = new HashMap<>();

	@PostConstruct
	private void initNotes() {
		releaseNotes.put("version-1.0", Arrays.asList("Home Page created", "Added a new feature"));
		releaseNotes.put("version-2.0", Arrays.asList("About us Page created", "Added two new feature"));
	}

	@ReadOperation
	public Map<String, List<String>> fetchReleaseNotes() {
		return releaseNotes;
	}

	@ReadOperation
	public List<String> fetchReleaseNotesByVersion(@Selector String version) {
		return releaseNotes.get(version);
	}

	@WriteOperation
	public void addReleaseNotesByVersion(@Selector String version, String releaseNote) {
		releaseNotes.put(version, Arrays.stream(releaseNote.split(",")).collect(Collectors.toList()));
	}

	@DeleteOperation
	public void addReleaseNotesByVersion(@Selector String version) {
		releaseNotes.remove(version);
	}
}
