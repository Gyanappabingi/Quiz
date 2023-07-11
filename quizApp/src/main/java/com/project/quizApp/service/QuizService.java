package com.project.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.quizApp.model.Question;
import com.project.quizApp.model.QuestionWrapper;
import com.project.quizApp.model.Quiz;
import com.project.quizApp.model.Response;
import com.project.quizApp.repository.QuestionRepository;
import com.project.quizApp.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity<String> createQuiz(String category,int numQ,String title){
		List<Question> questions= questionRepository.getRandomQuestions(category,numQ);
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizRepository.save(quiz);
		return new ResponseEntity<>("quiz created",HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id){
		Optional<Quiz> quiz=quizRepository.findById(id);
		
		List<Question> questionsfromDB=quiz.get().getQuestions();
		
		List<QuestionWrapper> questionsforuser=new ArrayList<>();
		
		for(Question q:questionsfromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsforuser.add(qw);
		
		}
		try {
		return new ResponseEntity<>(questionsforuser,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Integer> calculateResult(int id,List<Response> responses){
		Quiz quiz=quizRepository.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
			right++;
		i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
		
	}
	
	
	
}
