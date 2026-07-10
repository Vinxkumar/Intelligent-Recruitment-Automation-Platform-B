package com.example.RecruitmentAutomationPlatform.controller;

import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobRequestDto;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobResponseDto;
import com.example.RecruitmentAutomationPlatform.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/job")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDto> newJob(@RequestBody JobRequestDto jobRequestDto) {
        log.info("REST: request for new job with title {} by company {}", jobRequestDto.getTitle(), jobRequestDto.getCompanyName());
        return new ResponseEntity<>(jobService.createJob(jobRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        log.info("REST: request to delete job with id: {}", id);
        return ResponseEntity.ok(jobService.deleteJobById(id));
    }
}
