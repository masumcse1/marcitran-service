package com.ufril.medtran.dto.common;

import com.ufril.medtran.enumeration.ServiceType;
import com.ufril.medtran.enumeration.UserType;

import java.util.Date;

/**
 * @author moin
 */
public class ReviewDTO {

    private Long id;
    private String reviewer;
    private UserType reviewerType;
    private String reviewed;
    private UserType reviewedType;
    private Long serviceId;
    private ServiceType serviceType;
    private Integer rating;
    private String review;
    private Date reviewDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public UserType getReviewerType() {
        return reviewerType;
    }

    public void setReviewerType(UserType reviewerType) {
        this.reviewerType = reviewerType;
    }

    public String getReviewed() {
        return reviewed;
    }

    public void setReviewed(String reviewed) {
        this.reviewed = reviewed;
    }

    public UserType getReviewedType() {
        return reviewedType;
    }

    public void setReviewedType(UserType reviewedType) {
        this.reviewedType = reviewedType;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReviewDTO{");
        sb.append("id=").append(id);
        sb.append(", reviewer='").append(reviewer).append('\'');
        sb.append(", reviewerType=").append(reviewerType);
        sb.append(", reviewed='").append(reviewed).append('\'');
        sb.append(", reviewedType=").append(reviewedType);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", rating=").append(rating);
        sb.append(", review='").append(review).append('\'');
        sb.append(", reviewDate=").append(reviewDate);
        sb.append('}');
        return sb.toString();
    }
}
