package com.project.jobsonnar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.project.jobsonnar.model.Job;
import com.project.jobsonnar.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobSonnarApplicationTests {

	@Test
	void listNull() {
		JobResponse response = new JobResponse();
		List<Job> result = JobService.safeJobs(response);
		assertTrue(result.isEmpty());
	}

	@Test
	void listFull() {
		Job vacancy = new Job();
		JobResponse envelope = new JobResponse();
		envelope.setData(List.of(vacancy));
		List<Job> result = JobService.safeJobs(envelope);
		assertEquals(1, result.size());
	}

}
