package com.mathewzvk.firstjobapp.review.model;

import com.mathewzvk.firstjobapp.company.entity.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
    private String title;
    private String description;
    private String rating;
}
