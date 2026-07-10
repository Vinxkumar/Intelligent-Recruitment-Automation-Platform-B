package com.example.RecruitmentAutomationPlatform.dto.JobDto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JobResponseDto {

    private Long id;

    private String title;

    private String companyName;

    private Date createdAt;

}
