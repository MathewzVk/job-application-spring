package com.mathewzvk.firstjobapp.company.model;

import com.mathewzvk.firstjobapp.job.entity.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
}
