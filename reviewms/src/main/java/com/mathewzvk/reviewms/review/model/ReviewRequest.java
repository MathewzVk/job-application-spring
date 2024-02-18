package com.mathewzvk.reviewms.review.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
    private String title;
    private String description;
    private String rating;
    private Long companyId;
}
