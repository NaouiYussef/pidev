package com.example.pidev.DAO.Entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity

public class FeedbackVald extends Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;




    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public FeedbackVald( ) {

    }

    public FeedbackVald(Long id, String title, String feedbackText, String author, Rating rating, LocalDate t_stamp,  Type type, String note) {
        super(id, title, feedbackText, author, rating, t_stamp, type);
        this.note = note;
    }

    public FeedbackVald(Long id,String title, String feedbackText, String author, Rating rating, LocalDate t_stamp, Product product, User user, Type type, String note) {
        super(title, feedbackText, author, rating, t_stamp, product, user, type);
        this.note = note;
    }


}
