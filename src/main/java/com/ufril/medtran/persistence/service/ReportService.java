package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.report.PortfolioDTO;

import java.util.List;

public interface ReportService {
    List<PortfolioDTO> getPortfolioGrowth(int currentYear, int pastYear);
    int getCustomerRetention();
}
