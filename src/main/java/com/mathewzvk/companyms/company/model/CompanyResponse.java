package com.mathewzvk.companyms.company.model;

import com.mathewzvk.firstjobapp.job.entity.Job;
import com.mathewzvk.firstjobapp.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private List<Job> jobs;
    private List<Review> reviews;
}
