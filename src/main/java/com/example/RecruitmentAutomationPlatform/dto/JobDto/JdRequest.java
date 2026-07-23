package com.example.RecruitmentAutomationPlatform.dto.JobDto;

import com.example.RecruitmentAutomationPlatform.Types.EmploymentType;
import com.example.RecruitmentAutomationPlatform.Types.ExperienceRange;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JdRequest{

    private String title;

    private String companyName;
    private String pQualification;
    private String mQualification;
    private List<String> techStack;
//    private ExperienceRange expRange;

}
