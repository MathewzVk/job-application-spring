package com.mathewzvk.jobms.job.service;



import com.mathewzvk.jobms.job.dto.JobWithCompanyDTO;
import com.mathewzvk.jobms.job.entity.Job;
import com.mathewzvk.jobms.job.model.JobRequest;

import java.util.List;

public interface JobService {
    public List<JobWithCompanyDTO> findAllJobs();

    void addJob(JobRequest jobRequest);

    Job findJobById(Long id);

    Job updateJob(Long id, JobRequest jobRequest);

    void deleteJob(Long id);
}
