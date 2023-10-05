package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "interviewer_review")
public class InterviewerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interviewer_review_seq_gen")
    @SequenceGenerator(name = "interviewer_review_seq_gen", sequenceName = "interviewer_review_seq_gen", allocationSize = 1)
    private Integer id;

    @Column(name = "candidate_strength", columnDefinition = "text", nullable = false)
    private String candidateStrength;

    @Column(name = "candidate_weakness", columnDefinition = "text", nullable = false)
    private String candidateWeakness;

    @Column(name = "career_plan", nullable = false)
    private String careerPlan;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "commitment_years", nullable = false)
    private Integer commitmentYears;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_interview_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_interviewerreview_candidateinterview")
    )
    private CandidateInterview candidateInterview;
}
