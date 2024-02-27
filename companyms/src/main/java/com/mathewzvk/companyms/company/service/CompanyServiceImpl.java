package com.mathewzvk.companyms.company.service;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.mathewzvk.companyms.company.clients.ReviewClient;
import com.mathewzvk.companyms.company.dto.ReviewMessage;
import com.mathewzvk.companyms.company.entity.Company;
import com.mathewzvk.companyms.company.model.CompanyRequest;
import com.mathewzvk.companyms.company.model.CompanyResponse;
import com.mathewzvk.companyms.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public String createCompany(CompanyRequest companyRequest) {
        Company company = Company.builder()
                .name(companyRequest.getName())
                .description(companyRequest.getDescription())
                .build();
        companyRepository.save(company);
        return company.toString();
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(companyRequest.getName());
            company.setDescription(companyRequest.getDescription());
            companyRepository.save(company);
            return mapToCompanyResponse(company);
        }else {
            throw new NoSuchElementException("Company with ID " + id + " not found");
        }
    }

    @Override
    public String deleteCompany(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            companyRepository.delete(company);
            return "Company Deleted";
        }else{
            throw new NoSuchElementException("Company with ID " + id + " not found");
        }
    }

    @Override
    public CompanyResponse findCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            return mapToCompanyResponse(company);
        }else {
            throw new NoSuchElementException("Company with ID " + id + " not found");
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println("Rating ========> " + reviewMessage.getRating());
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException("No Company with ID : " + reviewMessage.getCompanyId()));
        double avgRating = reviewClient.getAverageRating(company.getId());
        company.setRating(avgRating);
        companyRepository.save(company);
    }

    private CompanyResponse mapToCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .build();
    }
}
