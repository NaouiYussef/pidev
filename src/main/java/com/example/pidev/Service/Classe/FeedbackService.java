package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.Feedback;
import com.example.pidev.DAO.Entities.FeedbackVald;
import com.example.pidev.DAO.Entities.Product;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.FeedbackRepository;
import com.example.pidev.DAO.Repositories.FeedbackValdRepository;
import com.example.pidev.DAO.Repositories.UserRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.pidev.Service.Interface.IFeedbackService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class FeedbackService implements IFeedbackService{
    FeedbackRepository feedbackRepository;
    UserRepositories userRepositories;
    FeedbackValdRepository feedbackValdRepository;

    @Override
    public List<Feedback> retrieveAllFeedbacks() {
        return feedbackRepository.findFeedbacksByTypeIs();

    }

    @Override
    public Feedback addFeedback(Feedback a,int id ) {
        a.setUser(userRepositories.findById(id).get());
        feedbackRepository.save(a);
        return a;
    }
    @Override
    public  Feedback affectFedP(Product p,User u, Feedback a){
        a.setProduct(p);
        a.setUser(u);
        feedbackRepository.save(a);
        return a;
    }
    @Override
    public Feedback updateFeedback(Feedback a) {
        feedbackRepository.save(a);
        return a;
    }

    @Override
    public Feedback retrieveFeedback(Long id) {
        return feedbackRepository.findById(id).get();

    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);

    }
    @Override
    @Scheduled(cron = "* * * * */10 *")
    public void RecExpire()
    {
        LocalDate beforedate=LocalDate.now().minusDays(2);

        List<Feedback> listab=feedbackRepository.findFeedbacksByT_stampBefore(beforedate);

        for (Feedback a:listab) {
            System.out.println(a.toString()+"wawaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            if(a.getType().equals(Feedback.Type.NonV)  ) {
                FeedbackVald F= new FeedbackVald(a.getId(), a.getTitle(),a.getFeedbackText(),a.getAuthor(),a.getRating(),a.getT_stamp(),a.getProduct(), a.getUser(), Feedback.Type.V,"Expire");
                F.setNote("Expire");
                System.out.println(a.toString()+"XAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

                feedbackValdRepository.save(F);

                feedbackRepository.deleteById(a.getId());
            }
        }
    }
    @Override
    public void Vald(Feedback a,String s)
    {
                Feedback f = feedbackRepository.findById(a.getId()).get();
                System.out.println(f.getAuthor()+f.getType());
                f.setType(Feedback.Type.NonV);
                FeedbackVald F= new FeedbackVald(f.getId(), f.getTitle(),f.getFeedbackText(),f.getAuthor(),f.getRating(),f.getT_stamp(),f.getProduct(), f.getUser(),Feedback.Type.V,s);

                feedbackValdRepository.save(F);

                feedbackRepository.deleteById(f.getId());


    }

}
