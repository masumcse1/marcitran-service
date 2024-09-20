package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.common.Location;
import com.ufril.medtran.persistence.domain.common.Station;
import com.ufril.medtran.persistence.repository.common.LocationRepository;
import com.ufril.medtran.persistence.repository.common.StationRepository;
import com.ufril.medtran.persistence.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {
}
