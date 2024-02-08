package com.mathewzvk.firstjobapp.company.service;

import com.mathewzvk.firstjobapp.company.entity.Company;
import com.mathewzvk.firstjobapp.company.model.CompanyRequest;
import com.mathewzvk.firstjobapp.company.model.CompanyResponse;
import com.mathewzvk.firstjobapp.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public String createCompany(CompanyRequest companyRequest) {
        Company company = Company.builder()
                .name(companyRequest.getName())
                .description(companyRequest.getDescription())
                .jobs(companyRequest.getJobs())
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
            company.setJobs(companyRequest.getJobs());
            company.setReviews(companyRequest.getReviews());
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

    private CompanyResponse mapToCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .jobs(company.getJobs())
                .reviews(company.getReviews())
                .build();
    }
}
