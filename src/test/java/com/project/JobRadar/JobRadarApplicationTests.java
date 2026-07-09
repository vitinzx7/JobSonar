package com.project.JobRadar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobRadarApplicationTests {

	@Test
	void listNull() {
		JobResponse response = new JobResponse();
		List<Job> result = JobRadarApplication.safeJobs(response);
		assertTrue(result.isEmpty());
	}

	@Test
	void listFull() {
		Job vacancy = new Job();
		JobResponse envelope = new JobResponse();
		envelope.setData(List.of(vacancy));
		List<Job> result = JobRadarApplication.safeJobs(envelope);
		assertEquals(1, result.size());
	}

}
