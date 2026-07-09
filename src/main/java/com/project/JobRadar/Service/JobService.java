package com.project.JobRadar.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.project.JobRadar.Job;
import com.project.JobRadar.JobResponse;
import java.util.List;

@Service
public class JobService {

    public List<Job> searchJobs() {

        RestClient client = RestClient.create();

		JobResponse response = client.get()
				.uri("https://employability-portal.gupy.io/api/v1/jobs?jobName=estagio Ti")
				.retrieve()
				.body(JobResponse.class);

        return safeJobs(response).stream().limit(5).toList();
	}

	public static List<Job> safeJobs(JobResponse response) {
		if (response.getData() == null) {
			return List.of();
		}
		return response.getData();
	}
    
}
