package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
    @Query("SELECT f FROM Feedback f WHERE f.t_stamp < :twoDaysAgo" )
    List<Feedback> findFeedbacksByT_stampBefore(@Param("twoDaysAgo") LocalDate beforeDate);
    @Query("SELECT f FROM Feedback f WHERE f.type = 1" )
    List<Feedback> findFeedbacksByTypeIs();

}
