package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "evaluation_criteria")
public class EvaluationCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaluation_criteria_seq_gen")
    @SequenceGenerator(name = "evaluation_criteria_seq_gen", sequenceName = "evaluation_criteria_seq_gen", allocationSize = 1)
    private Integer id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
