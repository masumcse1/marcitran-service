package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.common.ClaimDTO;
import com.ufril.medtran.persistence.domain.common.Claim;
import com.ufril.medtran.persistence.repository.common.ClaimRepository;
import com.ufril.medtran.persistence.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public List<ClaimDTO> getAllClaimByCompanyId(int companyId, Pageable pageable) {
        return claimRepository.getAllClaimByCompanyId(companyId, pageable);
    }

    @Override
    public ClaimDTO getClaimById(int id) {
        return claimRepository.getClaimById(id);
    }

    @Override
    @Transactional
    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    @Transactional
    public Claim updateClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public boolean deleteClaim(int id) {
        claimRepository.delete(id);
        return true;
    }
}
