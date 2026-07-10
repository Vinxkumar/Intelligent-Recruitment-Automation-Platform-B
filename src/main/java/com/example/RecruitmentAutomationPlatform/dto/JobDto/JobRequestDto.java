package com.example.RecruitmentAutomationPlatform.dto.JobDto;

import com.example.RecruitmentAutomationPlatform.Types.EmploymentType;
import com.example.RecruitmentAutomationPlatform.Types.WorkType;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequestDto {

    private String title;

    private String companyName;

    @Email
    private String companyEmail;

    private String pQualification;

    @Nullable
    private String mQualification;

    @Nullable
    private String jd;

    private List<String> techStack;

    private String expRange;

    @Nullable
    private String location;

    @Nullable
    private String compensation;

    private EmploymentType empType;

    private WorkType workType;

    @Nullable
    private String vacancies;

    @Nullable
    private Date deadLine;

    private Date createdAt;
}
