package com.mathewzvk.firstjobapp.job.service;

import com.mathewzvk.firstjobapp.job.entity.Job;
import com.mathewzvk.firstjobapp.job.model.JobRequest;

import java.util.List;

public interface JobService {
    public List<Job> findAllJobs();

    void addJob(JobRequest jobRequest);

    Job findJobById(Long id);

    Job updateJob(Job job);

    void deleteJob(Long id);
}
