package com.mathewzvk.firstjobapp.job.service;

import com.mathewzvk.firstjobapp.job.entity.Job;
import com.mathewzvk.firstjobapp.job.model.JobRequest;
import com.mathewzvk.firstjobapp.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{


    private final JobRepository jobRepository;

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void addJob(JobRequest jobRequest) {
        Job job = Job.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .minSalary(jobRequest.getMinSalary())
                .maxSalary(jobRequest.getMaxSalary())
                .location(jobRequest.getLocation())
                .company(jobRequest.getCompany())
                .build();
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job updateJob(Long id, JobRequest jobRequest) {
        Optional<Job> oldJobOptional = jobRepository.findById(id);
        if(oldJobOptional.isPresent()){
            Job oldJob = oldJobOptional.get();

            oldJob.setTitle(jobRequest.getTitle());
            oldJob.setDescription(jobRequest.getDescription());
            oldJob.setMinSalary(jobRequest.getMinSalary());
            oldJob.setMaxSalary(jobRequest.getMaxSalary());
            oldJob.setLocation(jobRequest.getLocation());
            oldJob.setCompany(jobRequest.getCompany());
            return jobRepository.save(oldJob);
        }else {
            throw new NoSuchElementException("Job with ID " + id + " not found");
        }
    }

    @Override
    public void deleteJob(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            jobRepository.delete(job);
        }else {
            throw new NoSuchElementException("Job with ID " + id + " not found");
        }
    }
}
