package com.example.pidev.RestControllers;
import com.example.pidev.DAO.Entities.FeedbackVald;
import com.example.pidev.Service.Interface.IFeedbackService;
import com.example.pidev.Service.Interface.IFeedbackValdService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/feedbackvald")
public class FeedbackValdController {
    private IFeedbackService iFeedBackService;
    private IFeedbackValdService iFeedBackValdService;

    @PostMapping("/addFeedback")
    public FeedbackVald ajouter (@RequestBody FeedbackVald feedbackVald)
    {
        LocalDate i = LocalDate.now();
        feedbackVald.setT_stamp(i);
        return  iFeedBackValdService.addFeedbackVald(feedbackVald);
    }

    @PutMapping("/updatefeedback")
    public FeedbackVald update(@RequestBody FeedbackVald feedbackVald)
    {
        return iFeedBackValdService.updateFeedbackVald(feedbackVald);
    }
    @GetMapping("/showAll/{id}")
    public List<FeedbackVald> findAll(@PathVariable Long id)
    {
        return iFeedBackValdService.retrieveAllFeedbackValds(id);
    }
    @GetMapping("/showFeedById/{id}")
    public FeedbackVald AfficherByID(@PathVariable Long id)
    {
        return iFeedBackValdService.retrieveFeedbackVald(id);
    }

    @DeleteMapping("/deleteFeedbackbyID/{id}")
    public String delete(@PathVariable Long id)
    {
        iFeedBackValdService.deleteFeedbackVald(id);
        return "Deleted";
    }
}