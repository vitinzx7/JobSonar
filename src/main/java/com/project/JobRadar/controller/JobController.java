package com.project.JobRadar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.project.JobRadar.Job;
import com.project.JobRadar.Service.JobService;


@RestController
@RequestMapping
public class JobController {
    
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/jobs")
    public List<Job> getJobs() {
        return jobService.searchJobs();
    }
}
