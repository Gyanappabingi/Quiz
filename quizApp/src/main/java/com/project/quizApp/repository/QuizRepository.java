package com.project.quizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.quizApp.model.Quiz;
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
