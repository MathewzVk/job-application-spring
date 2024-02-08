package com.mathewzvk.firstjobapp.review.service;

import com.mathewzvk.firstjobapp.company.entity.Company;
import com.mathewzvk.firstjobapp.company.repository.CompanyRepository;
import com.mathewzvk.firstjobapp.review.entity.Review;
import com.mathewzvk.firstjobapp.review.model.ReviewRequest;
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
}
