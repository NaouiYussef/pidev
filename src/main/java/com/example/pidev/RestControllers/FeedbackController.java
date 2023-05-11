package com.example.pidev.RestControllers;
import com.example.pidev.DAO.Entities.Feedback;
import com.example.pidev.Service.Interface.IFeedbackService;
import com.example.pidev.Service.Interface.IProduct;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/feedback")
public class FeedbackController {
    private IFeedbackService iFeedBackService;
    private IProduct iProduct;
    private IUser iuser;

    @PostMapping("/addFeedback/{id}")
    public Feedback ajouter (@RequestBody Feedback feedback, @PathVariable int id)
    {
        LocalDate i = LocalDate.now();
        feedback.setT_stamp(i);
        feedback.setType(Feedback.Type.NonV);
        return  iFeedBackService.addFeedback(feedback,id);
    }
    @PostMapping("/afFP/{id}/{ids}")
    public Feedback affFP (@RequestBody Feedback feedback, @PathVariable Long id, @PathVariable int ids)
    {
        LocalDate i = LocalDate.now();
        feedback.setT_stamp(i);
        feedback.setType(Feedback.Type.NonV);
        return  iFeedBackService.affectFedP(iProduct.SelectById(id),iuser.SelectById(ids),feedback);
    }
    @GetMapping("/vald/{id}/{s}")
    public void vald (@PathVariable long id,@PathVariable String s)
    {

          iFeedBackService.Vald(iFeedBackService.retrieveFeedback(id),s);
    }


    @PutMapping("/updatefeedback")
    public Feedback update(@RequestBody Feedback feedback)
    {
        return iFeedBackService.updateFeedback(feedback);
    }
    @GetMapping("/showAll")
    public List<Feedback> findAll()
    {
        return iFeedBackService.retrieveAllFeedbacks();
    }
    @GetMapping("/showFeedById/{id}")
    public Feedback AfficherByID(@PathVariable Long id)
    {
        return iFeedBackService.retrieveFeedback(id);
    }

    @DeleteMapping("/deleteFeedbackbyID/{id}")
    public String delete(@PathVariable Long id)
    {
        iFeedBackService.deleteFeedback(id);
        return "Deleted";
    }

}
