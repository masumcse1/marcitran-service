package com.ufril.medtran.dto.dispatch;

public class CallsPerVehicleDTO {
    private String callSign;
    private long numberOfCalls;

    public CallsPerVehicleDTO(String callSign, long numberOfCalls) {
        this.callSign = callSign;
        this.numberOfCalls = numberOfCalls;
    }

    public String getCallSign() {return callSign;}

    public void setCallSign(String callSign) { this.callSign = callSign;}

    public long getNumberOfCalls() {return numberOfCalls;}

    public void setNumberOfCalls(long numberOfCalls) {this.numberOfCalls = numberOfCalls;}
}
