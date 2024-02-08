package com.mathewzvk.firstjobapp.review.repository;

import com.mathewzvk.firstjobapp.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);
}
