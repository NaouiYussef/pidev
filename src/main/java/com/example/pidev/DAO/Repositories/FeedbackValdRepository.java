package com.example.pidev.DAO.Repositories;
import com.example.pidev.DAO.Entities.FeedbackVald;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface FeedbackValdRepository extends JpaRepository<FeedbackVald, Long> {
    @Query("SELECT f FROM Feedback f WHERE f.t_stamp < :beforeDate")
    List<FeedbackVald> findFeedbacksByT_stampBefore(@Param("beforeDate") LocalDate beforeDate);
}
