package com.mathewzvk.companyms.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "review-ms")
public interface ReviewClient {

    @GetMapping("/api/reviews/averageRating")
    public Double getAverageRating(@RequestParam Long companyId);
}
