package com.example.RecruitmentAutomationPlatform.services;

import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobRequestDto;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobResponseDto;

public interface JobService {
    JobResponseDto createJob(JobRequestDto jobRequest);
    String deleteJobById(Long jobId);
}
