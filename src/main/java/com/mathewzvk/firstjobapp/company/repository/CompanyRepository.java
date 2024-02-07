package com.mathewzvk.firstjobapp.company.repository;

import com.mathewzvk.firstjobapp.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
