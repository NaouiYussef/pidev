package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.Feedback;
import com.example.pidev.DAO.Entities.Product;
import com.example.pidev.DAO.Entities.User;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> retrieveAllFeedbacks();

    Feedback addFeedback(Feedback a,int id);

    Feedback updateFeedback (Feedback a);

    Feedback retrieveFeedback (Long id);

    void deleteFeedback(Long id);
    public  Feedback affectFedP(Product p, User u, Feedback a);
    public void RecExpire();
    public void Vald(Feedback f,String s);
}
