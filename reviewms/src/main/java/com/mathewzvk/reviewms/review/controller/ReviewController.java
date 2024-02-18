package com.mathewzvk.reviewms.review.controller;



import com.mathewzvk.reviewms.review.entity.Review;
import com.mathewzvk.reviewms.review.model.ReviewRequest;
import com.mathewzvk.reviewms.review.model.ReviewResponse;
import com.mathewzvk.reviewms.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> findAll(@RequestParam Long companyId){
        return reviewService.findAll(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createReview(@RequestParam Long companyId, @RequestBody ReviewRequest reviewRequest){
        return reviewService.createReview(companyId, reviewRequest);
    }

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse findReview(@PathVariable Long reviewId){
        return reviewService.findReviewById(reviewId);
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest){
        return reviewService.updateReview(reviewId, reviewRequest);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReview(@PathVariable Long reviewId){
        return reviewService.delete(reviewId);
    }

}
