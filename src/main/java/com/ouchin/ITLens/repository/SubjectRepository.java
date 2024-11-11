package com.ouchin.ITLens.repository;

import com.ouchin.ITLens.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySurveyEditionId(Long surveyEditionId);
}
