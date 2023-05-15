package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizDTO {
    private String question;
    private List<String> options;
    private int rightAnswer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

