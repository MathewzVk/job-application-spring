package com.mathewzvk.jobms.job.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
    private Long id;
    private String name;
    private String description;
}
