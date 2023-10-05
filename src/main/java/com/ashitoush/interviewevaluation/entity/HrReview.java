package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hr_review")
public class HrReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_review_seq_gen")
    @SequenceGenerator(name = "hr_review_seq_gen", sequenceName = "hr_review_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "current_organization")
    private String currentOrganization;

    @Column(name = "current_salary", precision = 18, scale = 2)
    private BigDecimal currentSalary;

    @Column(name = "current_position", length = 50)
    private String currentPosition;

    @Column(name = "expected_salary", precision = 18, scale = 2, nullable = false)
    private BigDecimal expectedSalary;

    @Column(name = "notice_period", nullable = false)
    private Integer noticePeriod;

    @Column(name = "comments", columnDefinition = "text", nullable = false)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_interview_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_hr_reviewCandidate_interview")
    )
    private CandidateInterview candidateInterview;
}
