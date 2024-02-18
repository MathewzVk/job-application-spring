package com.mathewzvk.reviewms.review.service;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.mathewzvk.reviewms.review.entity.Review;
import com.mathewzvk.reviewms.review.model.ReviewRequest;
import com.mathewzvk.reviewms.review.model.ReviewResponse;
import com.mathewzvk.reviewms.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponse> findAll(Long companyId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);

        for(Review review: reviewList){
            System.out.println("Reviews ======= >.." +review.getTitle());
        }

        return reviewList.stream().map(this::mapToReviewResponse).collect(Collectors.toList());

    }

    @Override
    public String createReview(Long companyId, ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .title(reviewRequest.getTitle())
                .description(reviewRequest.getDescription())
                .rating(reviewRequest.getRating())
                .companyId(companyId)
                .build();
        if(companyId != null){
            reviewRepository.save(review);
            return "review added successfully";
        }else {
            throw new NoSuchElementException("No Company with ID " + companyId);
        }
    }

    @Override
    public ReviewResponse findReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            return mapToReviewResponse(review);
        }else{
            throw new NoSuchElementException("No Review with ID "+reviewId);
        }
    }

    @Override
    public String updateReview(Long reviewId, ReviewRequest reviewRequest) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent()){
                Review review = reviewOptional.get();
                review.setTitle(reviewRequest.getTitle());
                review.setDescription(reviewRequest.getDescription());
                review.setRating(reviewRequest.getRating());
                reviewRepository.save(review);
                return "Review Updated Successfully";
        }else {
            throw new NoSuchElementException("No Review with ID "+reviewId);
        }
    }

    @Override
    public String delete(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            reviewRepository.delete(review);
            return "Review deleted successfully";
        }else {
            throw new NoSuchElementException("No Review with ID " + reviewId);
        }
    }

    private ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .description(review.getDescription())
                .rating(review.getRating())
                .build();
    }
}
