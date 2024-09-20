package com.ufril.medtran.dto.common;

import com.ufril.medtran.enumeration.UserType;

/**
 * @author moin
 */
public class ReviewAvgDTO {

    private UserType role;
    private Double averageRating;
    private Integer reviewerCount;

    public ReviewAvgDTO() {}

    public ReviewAvgDTO(UserType role, Double averageRating, Integer reviewerCount) {
        this.role = role;
        this.averageRating = averageRating;
        this.reviewerCount = reviewerCount;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getReviewerCount() {
        return reviewerCount;
    }

    public void setReviewerCount(Integer reviewerCount) {
        this.reviewerCount = reviewerCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReviewAvgDTO{");
        sb.append("role=").append(role);
        sb.append(", averageRating=").append(averageRating);
        sb.append(", reviewerCount=").append(reviewerCount);
        sb.append('}');
        return sb.toString();
    }
}
