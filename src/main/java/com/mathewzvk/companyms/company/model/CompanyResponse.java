package com.mathewzvk.companyms.company.model;

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
