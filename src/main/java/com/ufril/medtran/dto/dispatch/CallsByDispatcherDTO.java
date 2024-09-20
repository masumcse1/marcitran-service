package com.ufril.medtran.dto.dispatch;

public class CallsByDispatcherDTO {

    private String name;
    private long numberOfCalls;

    public CallsByDispatcherDTO(String name, long numberOfCalls) {
        this.name = name;
        this.numberOfCalls = numberOfCalls;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public long getNumberOfCalls() {return numberOfCalls; }

    public void setNumberOfCalls(long numberOfCalls) {this.numberOfCalls = numberOfCalls;}
}
