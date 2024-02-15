package com.mathewzvk.companyms.company.service;



import com.mathewzvk.companyms.company.entity.Company;
import com.mathewzvk.companyms.company.model.CompanyRequest;
import com.mathewzvk.companyms.company.model.CompanyResponse;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    String createCompany(CompanyRequest companyRequest);

    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

    String deleteCompany(Long id);

    CompanyResponse findCompanyById(Long id);
}
