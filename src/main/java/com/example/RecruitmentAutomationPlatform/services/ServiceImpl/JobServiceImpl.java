package com.example.RecruitmentAutomationPlatform.services.ServiceImpl;

import com.example.RecruitmentAutomationPlatform.config.ModelMapperConfig;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobDto;
import com.example.RecruitmentAutomationPlatform.dto.JobDto.JobResponseDto;
import com.example.RecruitmentAutomationPlatform.entity.JobEntity;
import com.example.RecruitmentAutomationPlatform.exceptions.DuplicateJobException;
import com.example.RecruitmentAutomationPlatform.exceptions.NoSuchJobException;
import com.example.RecruitmentAutomationPlatform.repositories.JobEntityRepository;
import com.example.RecruitmentAutomationPlatform.services.JobService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobEntityRepository jobRepository;
    private final ModelMapperConfig modelMapper;

    @Transactional
    @Override
    public JobResponseDto createJob(JobDto jobRequest) {
        if(jobRepository.existsByTitleAndCompanyName(jobRequest.getTitle(), jobRequest.getCompanyName())) {
            throw new DuplicateJobException("Job with this title  already exists for company  "+ jobRequest.getCompanyName());
        }
        JobEntity newJobEntity = mapToEntity(jobRequest);

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
