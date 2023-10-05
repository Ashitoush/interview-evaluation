package com.ashitoush.interviewevaluation.entity;

import com.ashitoush.interviewevaluation.enums.InterviewMode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "candidate_interview")
public class CandidateInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_interview_seq_gen")
    @SequenceGenerator(name = "candidate_interview_seq_gen", sequenceName = "candidate_interview_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "position_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_candidateInterview_position")
    )
    private Position positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_candidateInterview_department")
    )
    private Department department;

    @Column(name = "cv_url", nullable = false)
    private String cvUrl;

    @Column(name = "application_letter", columnDefinition = "text")
    private String applicationLetter;

    @Column(name = "interview_mode", nullable = false)
    private InterviewMode interviewMode;

    @Column(name = "interview_date", nullable = false)
    private LocalDate interviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_candidateInterview_candidate")
    )
    private Candidate candidate;

    @ManyToMany(mappedBy = "candidateInterview")
    private List<User> users;
}
