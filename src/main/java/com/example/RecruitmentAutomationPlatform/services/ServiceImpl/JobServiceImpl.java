package com.example.RecruitmentAutomationPlatform.services.ServiceImpl;

import com.example.RecruitmentAutomationPlatform.config.ModelMapperConfig;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JdRequest;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JdResponse;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobDto;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobResponseDto;
import com.example.RecruitmentAutomationPlatform.entity.JobEntity;
import com.example.RecruitmentAutomationPlatform.exceptions.DuplicateJobException;
import com.example.RecruitmentAutomationPlatform.exceptions.JdGenerateException;
import com.example.RecruitmentAutomationPlatform.exceptions.NoSuchJobException;
import com.example.RecruitmentAutomationPlatform.microservice.JdGenerator;
import com.example.RecruitmentAutomationPlatform.repositories.JobEntityRepository;
import com.example.RecruitmentAutomationPlatform.services.JobService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobEntityRepository jobRepository;
    private final ModelMapperConfig modelMapper;
    private final JdGenerator jdGenerator;

    @Transactional
    @Override
    public JobResponseDto createJob(JobDto jobRequest) {
        log.info("[JobService] Looking for job with title {} in company: {}", jobRequest.getTitle(), jobRequest.getCompanyName());
        if(jobRepository.existsByTitleAndCompanyName(jobRequest.getTitle(), jobRequest.getCompanyName())) {
            throw new DuplicateJobException("Job with this title  already exists for company  "+ jobRequest.getCompanyName());
        }
        JobEntity newJobEntity = mapToEntity(jobRequest);

        log.info("[JobService] Calling JD Generation Service for job with Id: {}", newJobEntity.getId());
//
//        new JdRequest();
//        try {
            JdRequest jdRequest = JdRequest.builder()
                    .title(newJobEntity.getTitle())
                    .techStack(newJobEntity.getTechStack())
//                    .expRange(newJobEntity.getExpRange())
                    .companyName(newJobEntity.getCompanyName())
                    .pQualification(newJobEntity.getPQualification())
                    .mQualification(newJobEntity.getMQualification())
                    .build();
            JdResponse jd = jdGenerator.generateJd(jdRequest);
            newJobEntity.setJd(jd.getJd());
//        } catch (Exception e) {
//            newJobEntity.setJd(jobRequest.getJd());
//            throw new JdGenerateException("Failed to Generate JD for Job");
//        }

        log.info("[JobService] Saved Job Successfully with Id:{}", newJobEntity.getId());
        final JobEntity savedJobEntity = jobRepository.save(newJobEntity);

        return mapToResponse(savedJobEntity);
    }

    @Transactional
    @Override
    public String deleteJobById(Long jobId) {
        if(!jobRepository.existsById(jobId)) {
            throw new NoSuchJobException("no job found with id: " + jobId);
        }
        jobRepository.deleteById(jobId);
        return "Deleted Job with Id" + jobId;
    }

    @Override
    public List<JobDto> listOfJobs() {
        List<JobEntity> jobs = jobRepository.findAll();
        return mapToDtoList(jobs);
    }


    private JobEntity mapToEntity(JobDto job) {
        return modelMapper.modelMapper().map(job, JobEntity.class);
    }

    private JobResponseDto mapToResponse(JobEntity job) {
        return new JobResponseDto(
                job.getId(),
                job.getTitle(),
                job.getCompanyName(),
                job.getCreatedAt()
        );
    }

    private List<JobDto> mapToDtoList(List<JobEntity> jobEntities) {
        return jobEntities.stream().map(
                jobs -> modelMapper.modelMapper().map(jobs, JobDto.class)
        ).toList();
    }



}
