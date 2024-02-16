package com.mathewzvk.jobms.job.service;


import com.mathewzvk.jobms.job.dto.JobWithCompanyDTO;
import com.mathewzvk.jobms.job.entity.Job;
import com.mathewzvk.jobms.job.external.Company;
import com.mathewzvk.jobms.job.model.JobRequest;
import com.mathewzvk.jobms.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{


    private final JobRepository jobRepository;

    @Override
    public List<JobWithCompanyDTO> findAllJobs() {
        List<Job> jobList = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOList = new ArrayList<>();
        return jobList.stream().map(this::mapToJobWithCompanyDTO).collect(Collectors.toList());
   }

    private JobWithCompanyDTO mapToJobWithCompanyDTO(Job job) {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/api/companies/" + job.getCompanyId(), Company.class);
        if(company != null) {
            return JobWithCompanyDTO.builder()
                    .job(job)
                    .company(company)
                    .build();
        }else {
            throw new NoSuchElementException("No Company related to Job With ID : " + job.getId());
        }
    }

    @Override
    public void addJob(JobRequest jobRequest) {
        Job job = Job.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .minSalary(jobRequest.getMinSalary())
                .maxSalary(jobRequest.getMaxSalary())
                .location(jobRequest.getLocation())
                .companyId(jobRequest.getCompanyId())
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
            oldJob.setCompanyId(jobRequest.getCompanyId());
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
