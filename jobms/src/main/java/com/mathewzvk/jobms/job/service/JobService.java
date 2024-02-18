package com.mathewzvk.jobms.job.service;



import com.mathewzvk.jobms.job.dto.JobDto;
import com.mathewzvk.jobms.job.entity.Job;
import com.mathewzvk.jobms.job.model.JobRequest;

import java.util.List;

public interface JobService {
    public List<JobDto> findAllJobs();

    void addJob(JobRequest jobRequest);

    JobDto findJobById(Long id);

    Job updateJob(Long id, JobRequest jobRequest);

    void deleteJob(Long id);
}
