package com.ouchin.ITLens.repository;

import com.ouchin.ITLens.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    boolean existsByTitle(String title);
}
