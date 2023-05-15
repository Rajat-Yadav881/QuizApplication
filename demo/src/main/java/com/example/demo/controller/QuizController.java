package com.example.demo.controller;

import com.example.demo.model.QuizDTO;
import com.example.demo.model.QuizStatus;
import com.example.demo.model.Quizes;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Quizes> createQuiz(@RequestBody QuizDTO quizDTO) {
        Quizes createdQuiz = quizService.createQuiz(quizDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

    @GetMapping("/active")
    public ResponseEntity<Quizes> getActiveQuiz() {
        Quizes activeQuiz = quizService.getActiveQuiz();
        if (activeQuiz != null) {
            return ResponseEntity.ok(activeQuiz);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<Integer> getQuizResult(@PathVariable("id") Long quizId) {
        Quizes quiz = quizService.getQuizById(quizId);
        if (quiz != null && quiz.getStatus() == QuizStatus.FINISHED) {
            return ResponseEntity.ok(quiz.getRightAnswer());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quizes>> getAllQuizzes() {
        List<Quizes> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }
}
