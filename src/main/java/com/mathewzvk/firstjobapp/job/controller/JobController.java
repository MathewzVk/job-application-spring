package com.mathewzvk.firstjobapp.job.controller;


import com.mathewzvk.firstjobapp.job.entity.Job;
import com.mathewzvk.firstjobapp.job.model.JobRequest;
import com.mathewzvk.firstjobapp.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    @Autowired
    private final JobService jobService;

    @GetMapping
    public List<Job> findAll(){
        return jobService.findAllJobs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addJob(@RequestBody JobRequest jobRequest){
        jobService.addJob(jobRequest);
    }

    @GetMapping("/{id}")
    public Job jobById(@PathVariable Long id){
        return jobService.findJobById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Job update(@RequestBody Job job){
        return jobService.updateJob(job);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        jobService.deleteJob(id);
    }

}
