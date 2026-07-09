package com.project.JobRadar.Service;

import org.springframework.stereotype.Service;
import com.project.JobRadar.Job;

@Service
public class JobService {
    
    public Job getExampleJob(){
        Job job = new Job();
        job.setName("Estaaaaaaaaaaaaaaaaaaaagio Backend Java");
        job.setCity("Remoto");
        job.setJobUrl("https://exemplo.com/vagas/estagio-backend-java");

        return job;
    }
}
