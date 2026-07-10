package com.example.RecruitmentAutomationPlatform.repositories;

import com.example.RecruitmentAutomationPlatform.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JobEntityRepository extends JpaRepository<JobEntity, Long> {

    Boolean existsByTitleAndCompanyName(String title, String companyName);
}