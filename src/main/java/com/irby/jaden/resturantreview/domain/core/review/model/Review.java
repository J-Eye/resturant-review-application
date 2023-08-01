package com.irby.jaden.resturantreview.domain.core.review.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int rating;

    @ManyToOne
    @JsonBackReference
    private Restaurant restaurant;

    @ManyToOne
    private User user;

    public Review() {
    }

    public Review(Long id, String title, String content, int rating, Restaurant restaurant, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.restaurant = restaurant;
        this.user = user;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }
}
