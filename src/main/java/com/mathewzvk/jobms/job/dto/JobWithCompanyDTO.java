package com.mathewzvk.jobms.job.dto;

import com.mathewzvk.jobms.job.entity.Job;
import com.mathewzvk.jobms.job.external.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
