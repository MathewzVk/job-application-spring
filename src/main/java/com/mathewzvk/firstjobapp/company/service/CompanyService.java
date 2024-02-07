package com.mathewzvk.firstjobapp.company.service;

import com.mathewzvk.firstjobapp.company.entity.Company;
import com.mathewzvk.firstjobapp.company.model.CompanyRequest;
import com.mathewzvk.firstjobapp.company.model.CompanyResponse;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    String createCompany(CompanyRequest companyRequest);

    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

    String deleteCompany(Long id);

    CompanyResponse findCompanyById(Long id);
}
