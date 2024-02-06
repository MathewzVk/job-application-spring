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
                .build();
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job updateJob(Job job) {
        Optional<Job> oldJobOptional = jobRepository.findById(job.getId());
        if(oldJobOptional.isPresent()){
            Job oldJob = oldJobOptional.get();

            oldJob.setTitle(job.getTitle());
            oldJob.setDescription(job.getDescription());
            oldJob.setMinSalary(job.getMinSalary());
            oldJob.setMaxSalary(job.getMaxSalary());
            oldJob.setLocation(job.getLocation());
            return jobRepository.save(oldJob);
        }else {
            throw new NoSuchElementException("Job with ID " + job.getId() + " not found");
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
