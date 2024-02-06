package com.mathewzvk.firstjobapp.job.repository;

import com.mathewzvk.firstjobapp.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
