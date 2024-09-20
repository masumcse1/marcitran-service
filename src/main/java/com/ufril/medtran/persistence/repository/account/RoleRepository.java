package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.dto.account.RoleDTO;
import com.ufril.medtran.persistence.domain.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by moin on 11/3/15.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
