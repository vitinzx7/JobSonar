package com.project.jobsonnar;

import java.util.List;

import com.project.jobsonnar.model.Job;

public class JobResponse {

    private List<Job> data;

    public List<Job> getData() {
        return data;
    }

    public void setData(List<Job> data) {
        this.data = data;
    }
}
