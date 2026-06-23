package com.project.JobRadar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class JobRadarApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobRadarApplication.class, args);

		RestClient client = RestClient.create();

		JobResponse response = client.get()
        .uri("https://employability-portal.gupy.io/api/v1/jobs?jobName=estagio Ti&state=Distrito Federal")
        .retrieve()
        .body(JobResponse.class);


		for(Job job : response.getData())
			{
				System.out.println(job.getName());
				System.out.println(job.getCity());

			}
	}

}
