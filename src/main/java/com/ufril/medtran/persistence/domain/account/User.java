package com.ufril.medtran.persistence.domain.account;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author sazzad
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column
    private String username;
    @Column(nullable = false, length = 500)
    private String password;
    private boolean twoFAEnabled;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPassChange;
    private int accessFailedCount;
    private boolean locked;
    @Column
    private String status;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "company_id", referencedColumnName = "id")
    private Company company;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    @Column(nullable = true)
    private int employeeId;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    // Constructors
    public User() {
        super();
    }

    public User(String username) {
        this.username = username;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Transient
    public boolean isUserActive() {
        return this.status.equalsIgnoreCase("ACTIVE");
    }

    @PrePersist
    protected void created() {
        this.created = this.lastUpdatedOn = new Date();
    }

    @PreUpdate
    protected void lastUpdatedOn() {
        this.lastUpdatedOn = new Date();
    }

    public boolean isTwoFAEnabled() {
        return twoFAEnabled;
    }

    public void setTwoFAEnabled(boolean twoFAEnabled) {
        this.twoFAEnabled = twoFAEnabled;
    }

    public Date getLastPassChange() {
        return lastPassChange;
    }

    public void setLastPassChange(Date lastPassChange) {
        this.lastPassChange = lastPassChange;
    }

    public int getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
