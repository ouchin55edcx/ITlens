package com.ouchin.ITLens.repository;

import com.ouchin.ITLens.entity.SurveyEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyEditionRepository extends JpaRepository<SurveyEdition, Long> {
}
