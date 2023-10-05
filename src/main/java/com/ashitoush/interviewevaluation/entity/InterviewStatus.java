package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "interview_status")
public class InterviewStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interview_status_seq_gen")
    @SequenceGenerator(name = "interview_status_seq_gen", sequenceName = "interview_status_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_interview_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_interviewStatus_candidateInterview")
    )
    private CandidateInterview candidateInterview;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "remarks", columnDefinition = "text", nullable = false)
    private String remarks;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
