package com.project.quizApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.quizApp.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> getQuestionsByCategory(String category);

	@Query(value = "select * from Question q where q.category=:category Order by Rand() limit :numQ ", nativeQuery = true)
	List<Question> getRandomQuestions(String category, int numQ);

}
