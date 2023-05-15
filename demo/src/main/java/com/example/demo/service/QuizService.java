package com.example.demo.service;

import com.example.demo.model.QuizDTO;
import com.example.demo.model.Quizes;

import java.util.List;

public interface QuizService {
    Quizes createQuiz(QuizDTO quizDTO);
    Quizes getActiveQuiz();
    Quizes getQuizById(Long quizId);
    List<Quizes> getAllQuizzes();
}
