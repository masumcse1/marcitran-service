package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by moin on 10/21/15.
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUserUsername(String username);
}
