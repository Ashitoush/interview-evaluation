package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "interview_evaluation_master")
public class InterviewEvaluationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interview_evaluation_seq_gen")
    @SequenceGenerator(name = "interview_evaluation_seq_gen", sequenceName = "interview_evaluation_seq_gen", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_interview_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_interviewEvaluationMaster_candidateInterview")
    )
    private CandidateInterview candidateInterview;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_evaluation_master_id")
    private List<InterviewEvaluationDetail> interviewEvaluationDetailList;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
