package com.ufril.medtran.dto.report;

public class PortfolioDTO {
    private int memberId;
    private String memberName;
    private int previousYearPatient;
    private int currentYearPatient;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getPreviousYearPatient() {
        return previousYearPatient;
    }

    public void setPreviousYearPatient(int previousYearPatient) {
        this.previousYearPatient = previousYearPatient;
    }

    public int getCurrentYearPatient() {
        return currentYearPatient;
    }

    public void setCurrentYearPatient(int currentYearPatient) {
        this.currentYearPatient = currentYearPatient;
    }
}
