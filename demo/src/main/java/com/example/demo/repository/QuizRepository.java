package com.example.demo.repository;

import com.example.demo.model.Quizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface QuizRepository extends JpaRepository<Quizes,Long> {

    @Query("SELECT q FROM Quizes q WHERE q.startDate <= CURRENT_TIMESTAMP AND q.endDate >= CURRENT_TIMESTAMP")
    Quizes findActiveQuiz(LocalDateTime now);


}
