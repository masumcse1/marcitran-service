package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by moin on 11/3/15.
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);
}
