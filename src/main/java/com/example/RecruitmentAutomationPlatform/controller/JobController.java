package com.example.RecruitmentAutomationPlatform.controller;

import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobDto;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobResponseDto;
import com.example.RecruitmentAutomationPlatform.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDto> newJob(@RequestBody JobDto jobDto) {
        log.info("REST: request for new job with title {} by company {}", jobDto.getTitle(), jobDto.getCompanyName());
        return new ResponseEntity<>(jobService.createJob(jobDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> listOfJObs() {
        log.info("REST: request to list all jobs");
        return ResponseEntity.ok(jobService.listOfJobs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        log.info("REST: request to delete job with id: {}", id);
        return ResponseEntity.ok(jobService.deleteJobById(id));
    }
}
