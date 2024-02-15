package com.mathewzvk.companyms.company.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CompanyRequest {

    private String name;
    private String description;

}
