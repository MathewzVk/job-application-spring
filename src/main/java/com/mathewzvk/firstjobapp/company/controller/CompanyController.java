package com.mathewzvk.firstjobapp.company.controller;

import com.mathewzvk.firstjobapp.company.entity.Company;
import com.mathewzvk.firstjobapp.company.model.CompanyRequest;
import com.mathewzvk.firstjobapp.company.model.CompanyResponse;
import com.mathewzvk.firstjobapp.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.createCompany(companyRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse updateJob(@PathVariable Long id, @RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(id, companyRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteJob(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse findCompany(@PathVariable Long id){
        return companyService.findCompanyById(id);
    }
}
