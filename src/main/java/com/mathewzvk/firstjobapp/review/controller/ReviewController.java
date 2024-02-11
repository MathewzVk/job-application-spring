package com.mathewzvk.firstjobapp.review.controller;


import com.mathewzvk.firstjobapp.review.entity.Review;
import com.mathewzvk.firstjobapp.review.model.ReviewRequest;
import com.mathewzvk.firstjobapp.review.model.ReviewResponse;
import com.mathewzvk.firstjobapp.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{companyId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Review> findAll(@PathVariable Long companyId){
        return reviewService.findAll(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createReview(@PathVariable Long companyId, @RequestBody ReviewRequest reviewRequest){
        return reviewService.createReview(companyId, reviewRequest);
    }

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse findReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return reviewService.findReviewById(companyId, reviewId);
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest){
        return reviewService.updateReview(companyId, reviewId, reviewRequest);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return reviewService.delete(companyId, reviewId);
    }

}
