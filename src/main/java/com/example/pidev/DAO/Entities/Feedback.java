package com.example.pidev.DAO.Entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity

public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String feedbackText;

    private String author;

    private Rating rating;
    private LocalDate t_stamp;
    @ManyToOne

    private Product product;

    @ManyToOne
    private User user;

    private Type type;
    public enum Type {
        V,
        NonV
    }
    public enum Rating {
        Bad,
        Poor,
        Fair,
        Good,
        Exellent
    }

    public Feedback() {
        // Default constructor required by JPA
    }

    public Feedback(String feedbackText, String author) {
        this.feedbackText = feedbackText;
        this.author = author;
    }

    public Feedback(Long id, String title, String feedbackText, String author, Rating rating) {
        this.id = id;
        this.title = title;
        this.feedbackText = feedbackText;
        this.author = author;
        this.rating = rating;
    }

    public Feedback(Long id,String title, String feedbackText, String author, Rating rating, LocalDate t_stamp,Type type) {
        this.id = id;
        this.title = title;
        this.feedbackText = feedbackText;
        this.author = author;
        this.rating = rating;
        this.t_stamp = t_stamp;
        this.type = type;

    }

    public Feedback(String title, String feedbackText, String author, Rating rating, LocalDate t_stamp, Product product, User user, Type type) {
        this.title = title;
        this.feedbackText = feedbackText;
        this.author = author;
        this.rating = rating;
        this.t_stamp = t_stamp;
        this.product = product;
        this.user = user;
        this.type = type;
    }
// Getters and Setters for id, feedbackText, and author fields

    // Getters and Setters for id, feedbackText, and author fields

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Rating getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public LocalDate getT_stamp() {
        return t_stamp;
    }

    public void setT_stamp(LocalDate t_stamp) {
        this.t_stamp = t_stamp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}