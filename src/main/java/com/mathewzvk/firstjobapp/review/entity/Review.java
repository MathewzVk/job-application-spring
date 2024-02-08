package com.mathewzvk.firstjobapp.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mathewzvk.firstjobapp.company.entity.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_review")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

}
