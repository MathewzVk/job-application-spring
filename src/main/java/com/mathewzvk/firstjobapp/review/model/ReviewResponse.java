package com.mathewzvk.firstjobapp.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mathewzvk.firstjobapp.company.entity.Company;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private String title;
    private String description;
    private String rating;
    private Company company;
}
