package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Integer> {
}
