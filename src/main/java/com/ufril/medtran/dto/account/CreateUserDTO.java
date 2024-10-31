package com.ufril.medtran.dto.account;

import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.validation.PasswordMatches;

/**
 * @author moin
 */
@PasswordMatches
public class CreateUserDTO {

    private String email;
    private String username;
    private String password;
    private String matchingPassword;
    private boolean twoFAEnabled;
    private boolean locked;
    private String status;
    private RoleType role;
    private int employeeId;
    private int companyId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTwoFAEnabled() {
        return twoFAEnabled;
    }

    public void setTwoFAEnabled(boolean twoFAEnabled) {
        this.twoFAEnabled = twoFAEnabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
