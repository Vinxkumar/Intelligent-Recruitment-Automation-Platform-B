package com.example.RecruitmentAutomationPlatform.services;

import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobDto;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobResponseDto;

import java.util.List;

public interface JobService {
    JobResponseDto createJob(JobDto jobRequest);
    String deleteJobById(Long jobId);
    List<JobDto> listOfJobs();
}
