package com.ufril.medtran.dto.common;

import com.ufril.medtran.validation.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author moin
 */
public class EmailSubscriptionDTO {

    @NotEmpty
    @NotNull
    @Size(min = 1)
    private String name;
    @ValidEmail
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailSubscriptionDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
