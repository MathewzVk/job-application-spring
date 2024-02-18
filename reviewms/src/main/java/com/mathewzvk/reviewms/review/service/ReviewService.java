package com.mathewzvk.reviewms.review.service;



import com.mathewzvk.reviewms.review.entity.Review;
import com.mathewzvk.reviewms.review.model.ReviewRequest;
import com.mathewzvk.reviewms.review.model.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> findAll(Long companyId);

    String createReview(Long companyId, ReviewRequest reviewRequest);

    ReviewResponse findReviewById(Long reviewId);

    String updateReview(Long reviewId, ReviewRequest reviewRequest);

    String delete(Long reviewId);
}
