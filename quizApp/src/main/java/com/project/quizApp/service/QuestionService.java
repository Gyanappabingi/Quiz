package com.project.quizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.quizApp.model.Question;
import com.project.quizApp.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity<String> postQuestion(Question question) {
		questionRepository.save(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<List<Question>> getAllQuestions(){
		List<Question> questions= questionRepository.findAll();
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}
	
	public ResponseEntity<List<Question>> getQuestionByCategory(String category){
		List<Question> questions=questionRepository.getQuestionsByCategory(category);
		try {
			return new ResponseEntity<>(questions,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
		
	}
	
	public ResponseEntity<Question> upadateQuestion(int id,Question question) {
		Question existingQuestion=questionRepository.findById(id).get();
		
		existingQuestion.setQuestionTitle(question.getQuestionTitle());
		existingQuestion.setOption1(question.getOption1());
		existingQuestion.setOption2(question.getOption2());
		existingQuestion.setOption3(question.getOption3());
		existingQuestion.setOption4(question.getOption4());
		existingQuestion.setRightAnswer(question.getRightAnswer());
		existingQuestion.setCategory(question.getCategory());
		existingQuestion.setDifficultylevel(question.getDifficultylevel());
		
		questionRepository.save(existingQuestion);
		return new ResponseEntity<>(questionRepository.save(existingQuestion),HttpStatus.OK);
		
		
		
		
	}
	
	public ResponseEntity<String> deleteQuestion(int id) {
		questionRepository.deleteById(id);
		return new ResponseEntity<>("deleted",HttpStatus.OK);
	}
	
}
