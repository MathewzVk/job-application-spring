package com.mathewzvk.firstjobapp.job.entity;

import com.mathewzvk.firstjobapp.company.entity.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_job")
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "min_salary")
    private String minSalary;

    @Column(name = "max_salary")
    private String maxSalary;

    private String location;

    @ManyToOne
    private Company company;

}
