package com.mathewzvk.firstjobapp.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mathewzvk.firstjobapp.job.entity.Job;
import com.mathewzvk.firstjobapp.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Job> jobs;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
