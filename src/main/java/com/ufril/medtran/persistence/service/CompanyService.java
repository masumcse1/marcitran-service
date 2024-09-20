package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.account.CompanyDTO;
import com.ufril.medtran.dto.account.CreateUserDTO;
import com.ufril.medtran.persistence.domain.account.Company;

import java.util.Map;

public interface CompanyService {

	Company saveCompany(Company company);

	Map<String, Object> createCompanyWithAdminUser(CreateUserDTO admin, CompanyDTO newCompany);
}
