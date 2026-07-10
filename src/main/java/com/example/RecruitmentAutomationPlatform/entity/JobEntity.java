package com.example.RecruitmentAutomationPlatform.entity;

import com.example.RecruitmentAutomationPlatform.Types.EmploymentType;
import com.example.RecruitmentAutomationPlatform.Types.WorkType;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(
        name = "job_posts"
)
@Getter
@Setter
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Email
    @Column(name = "company_mail", unique = false)
    private String companyEmail;

    @Column(name = "preferred_qualification")
    private String pQualification;

    @Nullable
    @Column(name = "minimum_qualification")
    private String mQualification;

    @Nullable
    @Column(name = "job_description")
    private String jd;

    @ElementCollection
    @Column(name = "tech_stack")
    private List<String> techStack;

    @Column(name = "experience_range")
    private String expRange;

    @Nullable
    @Column(name = "location")
    private String location;

    @Nullable
    @Column(name = "compensation")
    private String compensation;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType empType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "work_type")
    private WorkType workType;

    @Nullable
    @Column(name = "vacancies")
    private String vacancies;

    @Nullable
    @Column(name = "dead_line")
    private Date deadLine;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
}
