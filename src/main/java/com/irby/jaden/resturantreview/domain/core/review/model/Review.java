package com.irby.jaden.resturantreview.domain.core.review.model;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int rating;

    @JoinColumn(name = "UserEntity_id")
    private Long userId;

    @JoinColumn(name = "restaurant_id")
    private Long restaurnatId;


    public Review() {
    }

    public Review(Long id, String title, String content, int rating, Long userId, Long restaurnatId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.userId = userId;
        this.restaurnatId = restaurnatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurnatId() {
        return restaurnatId;
    }

    public void setRestaurnatId(Long restaurnatId) {
        this.restaurnatId = restaurnatId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", userId=" + userId +
                ", restaurnatId=" + restaurnatId +
                '}';
    }
}
