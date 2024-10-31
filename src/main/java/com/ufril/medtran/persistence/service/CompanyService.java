package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.account.CompanyDTO;
import com.ufril.medtran.dto.account.CreateUserDTO;

import java.util.Map;

public interface CompanyService {

    Map<String, Object> createCompanyWithAdminUser(CompanyDTO newCompany, CreateUserDTO admin);
}
