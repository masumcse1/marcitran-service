package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.ShiftCrewMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftCrewMemberRepository extends JpaRepository<ShiftCrewMembers, Integer> {

}
