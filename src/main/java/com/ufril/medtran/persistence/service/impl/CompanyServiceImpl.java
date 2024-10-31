package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.account.CompanyDTO;
import com.ufril.medtran.dto.account.CreateUserDTO;
import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.persistence.domain.account.Company;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.repository.account.CompanyRepository;
import com.ufril.medtran.persistence.service.CompanyService;
import com.ufril.medtran.persistence.service.UserService;
import com.ufril.medtran.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Map<String, Object> createCompanyWithAdminUser(CompanyDTO newCompany,
                                                          CreateUserDTO admin) {

        Company company = MapperUtils.mapDTOToCompany(newCompany);
        company.setActive(true);
        company = repository.save(company);

        admin.setCompanyId(company.getId());
        admin.setRole(RoleType.Administrator);
        User user = userService.createUser(admin, null);

        Map<String, Object> response = new HashMap<>();
        response.put("admin", MapperUtils.mapUserToProfileDTO(user));
        response.put("company", company);

        return response;
    }
}
