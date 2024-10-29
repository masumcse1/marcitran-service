package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.common.ClaimDTO;
import com.ufril.medtran.persistence.domain.common.Claim;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClaimService {

    List<ClaimDTO> getAllClaimByCompanyId(int companyId, Pageable pageable);

    ClaimDTO getClaimById(int id);

    Claim createClaim(Claim claim);

    Claim updateClaim(Claim claim);

    boolean deleteClaim(int id);
}
