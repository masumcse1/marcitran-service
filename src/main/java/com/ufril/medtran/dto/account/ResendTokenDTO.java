package com.ufril.medtran.dto.account;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author moin
 */
public class ResendTokenDTO {
    @NotEmpty
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ResendTokenDTO{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
