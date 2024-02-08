package com.mathewzvk.firstjobapp.review.service;

import com.mathewzvk.firstjobapp.review.entity.Review;
import com.mathewzvk.firstjobapp.review.model.ReviewRequest;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);

    String createReview(Long companyId, ReviewRequest reviewRequest);
}
