package com.mathewzvk.jobms.job.mapper;

import com.mathewzvk.jobms.job.dto.JobDto;
import com.mathewzvk.jobms.job.entity.Job;
import com.mathewzvk.jobms.job.external.Company;
import com.mathewzvk.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDto jobWithCompanyDTO(Job job, Company company, List<Review> reviewList){

        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .location(job.getLocation())
                .company(company)
                .reviews(reviewList)
                .build();
    }

}
