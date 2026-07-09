package com.project.JobRadar;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class JobRadarApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobRadarApplication.class, args);

		RestClient client = RestClient.create();

		JobResponse response = client.get()
				.uri("https://employability-portal.gupy.io/api/v1/jobs?jobName=estagio Ti")
				.retrieve()
				.body(JobResponse.class);

		for (Job job : safeJobs(response)) {
			System.out.println("--------------------------------------------");
			System.out.println(job.getName());
			System.out.println(job.getCity());
			System.out.println(job.getPublishedDate());
			
		}
	}

	public static List<Job> safeJobs(JobResponse response) {
		if (response.getData() == null) {
			return List.of();
		}
		return response.getData();
	}

}
