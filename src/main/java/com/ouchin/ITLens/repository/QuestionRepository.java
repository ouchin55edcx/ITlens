package com.ouchin.ITLens.repository;

import com.ouchin.ITLens.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySubjectId(Long subjectId);
}

