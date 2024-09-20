package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    Preference findByName(String name);
}
