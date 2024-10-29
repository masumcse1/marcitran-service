package com.ufril.medtran.dto.dispatch;

public class CallsPerDayNightDTO {

    private String period;
    private long noOfCalls;

    public CallsPerDayNightDTO(String period, long noOfCalls) {
        this.period = period;
        this.noOfCalls = noOfCalls;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public long getNoOfCalls() {
        return noOfCalls;
    }

    public void setNoOfCalls(long noOfCalls) {
        this.noOfCalls = noOfCalls;
    }
}
