package com.ufril.medtran.dto.account;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author moin
 */
public class ConfirmSignUpDTO {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfirmSignUpDTO{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
