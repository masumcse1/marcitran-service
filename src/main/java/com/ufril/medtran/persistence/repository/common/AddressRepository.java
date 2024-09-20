package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by moin on 11/24/15.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
