package com.ashitoush.interviewevaluation.entity;

import com.ashitoush.interviewevaluation.enums.RatingScale;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "interview_evaluation_detail")
public class InterviewEvaluationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interview_evaluation_detail_seq_gen")
    @SequenceGenerator(name = "interview_evaluation_detail_seq_gen", sequenceName = "interview_evaluation_detail_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "interview_evaluation_master_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_interviewEvaluationDetail_master")
    )
    private InterviewEvaluationMaster interviewEvaluationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "evaluation_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_interviewEvaluation_evaluationCriteria")
    )
    private EvaluationCriteria evaluationCriteria;

    @Column(name = "rating_scale", nullable = false)
    private RatingScale ratingScale;
}
