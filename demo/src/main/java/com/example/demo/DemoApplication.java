package com.example.demo;

import com.example.demo.model.QuizDTO;
import com.example.demo.model.Quizes;
import com.example.demo.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
public class DemoApplication {

	private QuizService quizService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<Quizes> getQuizById(@PathVariable Long quizId) {
		Quizes quizes = quizService.getQuizById(quizId);
		if (quizes != null) {
			return ResponseEntity.ok(quizes);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
