package com.mathewzvk.reviewms.review.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private Long id;
    private String title;
    private String description;
    private String rating;
    private Long companyId;
}
