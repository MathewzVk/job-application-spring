package com.mathewzvk.reviewms.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewMessage {
    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;
}
