package com.ufril.medtran.dto.account;

import com.ufril.medtran.enumeration.StatusType;

import javax.validation.constraints.NotNull;

/**
 * Created by moin on 12/11/15.
 */
public class SubscribeServiceDTO {
    @NotNull
    private Long serviceId;
    @NotNull
    private StatusType status;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SubscribeServiceDTO{" +
                "serviceId=" + serviceId +
                ", status=" + status +
                '}';
    }
}
