package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.dto.common.ClaimDTO;
import com.ufril.medtran.persistence.domain.common.Claim;
import com.ufril.medtran.persistence.domain.common.Lead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    @Query("SELECT new com.ufril.medtran.dto.common.ClaimDTO(c) " +
            "FROM Claim c WHERE c.companyId = ?1")
    List<ClaimDTO> findAllByCompanyId(int companyId, Pageable pageable);

    @Query("SELECT new com.ufril.medtran.dto.common.ClaimDTO(c) " +
            "FROM Claim c " +
            "WHERE c.id = :id")
    ClaimDTO findById(@Param("id") Integer id);
}
