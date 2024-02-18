package com.mathewzvk.jobms.job.dto;

import com.mathewzvk.jobms.job.external.Company;
import com.mathewzvk.jobms.job.external.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}
