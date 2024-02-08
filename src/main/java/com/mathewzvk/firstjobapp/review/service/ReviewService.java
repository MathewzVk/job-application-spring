package com.mathewzvk.firstjobapp.review.service;

import com.mathewzvk.firstjobapp.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);
}
