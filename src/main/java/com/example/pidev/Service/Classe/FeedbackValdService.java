package com.example.pidev.Service.Classe;
import com.example.pidev.DAO.Entities.Feedback;
import com.example.pidev.DAO.Entities.FeedbackVald;
import com.example.pidev.DAO.Repositories.FeedbackValdRepository;
import com.example.pidev.Service.Interface.IFeedbackValdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class FeedbackValdService implements IFeedbackValdService {
    FeedbackValdRepository feedbackValdRepository;
    @Override
    public List<FeedbackVald> retrieveAllFeedbackValds(Long id) {
        List<FeedbackVald> d = feedbackValdRepository.findAll();
        List<FeedbackVald> e = new ArrayList<>();
        for(FeedbackVald i:d) {
            if(i.getUser().getIdUser()==id)
            {
                e.add(i);
            }
        }
        return e;
    }


    @Override
    public FeedbackVald addFeedbackVald(FeedbackVald a) {
        feedbackValdRepository.save(a);
        return a;
    }

    @Override
    public FeedbackVald updateFeedbackVald(FeedbackVald a) {
        feedbackValdRepository.save(a);
        return a;
    }

    @Override
    public FeedbackVald retrieveFeedbackVald(Long id) {
        return feedbackValdRepository.findById(id).get();

    }

    @Override
    public void deleteFeedbackVald(Long id) {
        feedbackValdRepository.deleteById(id);

    }
}
