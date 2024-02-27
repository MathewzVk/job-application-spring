package com.mathewzvk.reviewms.review.messaging;

import com.mathewzvk.reviewms.review.dto.ReviewMessage;
import com.mathewzvk.reviewms.review.entity.Review;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review){
        ReviewMessage reviewMessage = ReviewMessage.builder()
                .id(review.getId())
                .title(review.getTitle())
                .description(review.getDescription())
                .rating(review.getRating())
                .companyId(review.getCompanyId())
                .build();
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }

}
