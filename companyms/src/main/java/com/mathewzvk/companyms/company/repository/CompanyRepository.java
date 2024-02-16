package com.mathewzvk.companyms.company.repository;


import com.mathewzvk.companyms.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
