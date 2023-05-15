package com.example.demo.service.impl;

import com.example.demo.model.QuizDTO;
import com.example.demo.model.QuizStatus;
import com.example.demo.model.Quizes;
import com.example.demo.repository.QuizRepository;
import com.example.demo.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Quizes createQuiz(QuizDTO quizDTO) {
        LocalDateTime now = LocalDateTime.now();
        if (quizDTO.getStartDate() == null) {
            quizDTO.setStartDate(now);
        }
        if (quizDTO.getEndDate() == null) {
            quizDTO.setEndDate(now.plusMinutes(5));
        }

        Quizes quiz = new Quizes();
        quiz.setQuestion(quizDTO.getQuestion());
        quiz.setOptions(quizDTO.getOptions());
        quiz.setRightAnswer(quizDTO.getRightAnswer());
        quiz.setStartDate(quizDTO.getStartDate());
        quiz.setEndDate(quizDTO.getEndDate());
        quiz.setStatus(getQuizStatus(now, quizDTO.getStartDate(), quizDTO.getEndDate()));

        return quizRepository.save(quiz);
    }

    @Override
    public Quizes getActiveQuiz() {
        LocalDateTime now = LocalDateTime.now();
        return quizRepository.findActiveQuiz(now);
    }

    @Override
    @Cacheable(value = "quizCache", key = "#quizId", unless = "#result == null")
    public Quizes getQuizById(Long quizId) {
        Optional<Quizes> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quizes quiz = optionalQuiz.get();
            // Convert the Quizes entity to QuizDTO
            QuizDTO quizDTO = new QuizDTO();
            quizDTO.setQuestion(quiz.getQuestion());
            quizDTO.setOptions(quiz.getOptions());
            quizDTO.setRightAnswer(quiz.getRightAnswer());
            quizDTO.setStartDate(quiz.getStartDate());
            quizDTO.setEndDate(quiz.getEndDate());
            Quizes quizes = modelMapper.map(quizDTO, Quizes.class);
            return quizes;
        }
        return null;
    }

    @Override
    public List<Quizes> getAllQuizzes() {
        return quizRepository.findAll();
    }

    private QuizStatus getQuizStatus(LocalDateTime currentTime, LocalDateTime startDate, LocalDateTime endDate) {
        if (currentTime.isBefore(startDate)) {
            return QuizStatus.INACTIVE;
        } else if (currentTime.isAfter(endDate)) {
            return QuizStatus.FINISHED;
        } else {
            return QuizStatus.ACTIVE;
        }
    }
}
