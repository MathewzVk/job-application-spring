package com.mathewzvk.firstjobapp.review.service;

import com.mathewzvk.firstjobapp.company.entity.Company;
import com.mathewzvk.firstjobapp.company.repository.CompanyRepository;
import com.mathewzvk.firstjobapp.review.entity.Review;
import com.mathewzvk.firstjobapp.review.model.ReviewRequest;
import com.mathewzvk.firstjobapp.review.model.ReviewResponse;
import com.mathewzvk.firstjobapp.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Review> findAll(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public String createReview(Long companyId, ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .title(reviewRequest.getTitle())
                .description(reviewRequest.getDescription())
                .rating(reviewRequest.getRating())
                .build();
        Optional<Company> companyOptional= companyRepository.findById(companyId);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            review.setCompany(company);
            reviewRepository.save(review);
            return "review added successfully";
        }else {
            throw new NoSuchElementException("No Company with ID "+companyId);
        }
    }

    @Override
    public ReviewResponse findReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = findAll(companyId);
        Review reviewFound = reviews.stream().filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
        if(reviewFound != null){
            return mapToReviewResponse(reviewFound);
        }else{
            throw new NoSuchElementException("No Review with ID "+reviewId);
        }
    }

    @Override
    public String updateReview(Long companyId, Long reviewId, ReviewRequest reviewRequest) {
        List<Review> reviewsList = findAll(companyId);
        Review review = reviewsList.stream().filter(rev -> rev.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
        if(review != null){
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
    public String delete(Long companyId, Long reviewId) {
        List<Review> reviewList = findAll(companyId);
        Optional<Review> reviewOptional = reviewList.stream().filter(review -> review.getId().equals(reviewId))
                .findFirst();
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
                .title(review.getTitle())
                .description(review.getDescription())
                .rating(review.getRating())
                .company(review.getCompany())
                .build();
    }
}
