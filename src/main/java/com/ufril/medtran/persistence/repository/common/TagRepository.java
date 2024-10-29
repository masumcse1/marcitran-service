package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByCompanyId(int companyId);
}
