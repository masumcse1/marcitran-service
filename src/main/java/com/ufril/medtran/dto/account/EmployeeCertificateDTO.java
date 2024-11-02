package com.ufril.medtran.dto.account;

import java.util.Date;
import java.util.List;

public class EmployeeCertificateDTO {

    public static class CertificateDTO {

        private int certificateId;
        private Date validity;

        public int getCertificateId() {
            return certificateId;
        }

        public void setCertificateId(int certificateId) {
            this.certificateId = certificateId;
        }

        public Date getValidity() {
            return validity;
        }

        public void setValidity(Date validity) {
            this.validity = validity;
        }
    }

    private int employeeId;
    private List<CertificateDTO> certificates;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public List<CertificateDTO> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateDTO> certificates) {
        this.certificates = certificates;
    }
}
