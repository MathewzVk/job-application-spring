package com.mathewzvk.jobms.job.service;


import com.mathewzvk.jobms.job.clients.CompanyClient;
import com.mathewzvk.jobms.job.clients.ReviewClient;
import com.mathewzvk.jobms.job.dto.JobDto;
import com.mathewzvk.jobms.job.entity.Job;
import com.mathewzvk.jobms.job.external.Company;
import com.mathewzvk.jobms.job.external.Review;
import com.mathewzvk.jobms.job.mapper.JobMapper;
import com.mathewzvk.jobms.job.model.JobRequest;
import com.mathewzvk.jobms.job.repository.JobRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    //int attempts = 0;

    @Autowired
    RestTemplate restTemplate;


    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDto> findAllJobs() {
        //System.out.println("Attempts =======> " + ++attempts);
        List<Job> jobList = jobRepository.findAll();
        List<JobDto> jobDtoList = new ArrayList<>();
        return jobList.stream().map(this::mapToJobWithCompanyDTO).collect(Collectors.toList());
   }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }


    private JobDto mapToJobWithCompanyDTO(Job job) {
        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviewList = reviewClient.getReviews(job.getCompanyId());
        return JobMapper.jobWithCompanyDTO(job, company, reviewList);
            //throw new NoSuchElementException("No Company related to Job With ID : " + job.getId());
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
    public JobDto findJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if(job != null){
            return mapToJobWithCompanyDTO(job);
        }else{
            throw new NoSuchElementException("No Job with ID : " + id);
        }
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
