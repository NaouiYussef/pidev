package com.example.pidev.Service.Interface;


import com.example.pidev.DAO.Entities.FeedbackVald;

import java.util.List;

public interface IFeedbackValdService {
    List<FeedbackVald> retrieveAllFeedbackValds(Long id);

    FeedbackVald addFeedbackVald(FeedbackVald a);

    FeedbackVald updateFeedbackVald (FeedbackVald a);

    FeedbackVald retrieveFeedbackVald (Long id);

    void deleteFeedbackVald(Long id);
}
