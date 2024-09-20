package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.dto.account.GetProfileDTO;

import java.util.List;

/**
 * @author moin
 */
public interface UserSearchRepository {

    List<GetProfileDTO> findNearbyUsers(Double latitude, Double longitude, Double radius, String requester);
}
