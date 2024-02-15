package com.mathewzvk.jobms.job.repository;


import com.mathewzvk.jobms.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
