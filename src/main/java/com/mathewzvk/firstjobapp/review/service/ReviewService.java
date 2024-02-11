package com.mathewzvk.firstjobapp.review.service;

import com.mathewzvk.firstjobapp.review.entity.Review;
import com.mathewzvk.firstjobapp.review.model.ReviewRequest;
import com.mathewzvk.firstjobapp.review.model.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);

    String createReview(Long companyId, ReviewRequest reviewRequest);

    ReviewResponse findReviewById(Long companyId, Long reviewId);

    String updateReview(Long companyId, Long reviewId, ReviewRequest reviewRequest);

    String delete(Long companyId, Long reviewId);
}
