package com.example.RecruitmentAutomationPlatform.dto.JobDto;

import com.example.RecruitmentAutomationPlatform.Types.EmploymentType;
import com.example.RecruitmentAutomationPlatform.Types.ExperienceRange;
import com.example.RecruitmentAutomationPlatform.Types.WorkType;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {

    private Long id;

    private String title; //done

    private String companyName; //done

    @Email
    private String companyEmail; //done

    private String pQualification; //done

    @Nullable
    private String mQualification;//done

    @Nullable
    private String jd; //done

    private List<String> techStack; //done


    private ExperienceRange expRange; //done

    @Nullable
    private String location; //done

    @Nullable
    private String compensation; //done

    private EmploymentType empType;  //done

    private WorkType workType;

    @Nullable
    private String vacancies;

    @Nullable
    private Date deadLine;

    private Date createdAt;
}
