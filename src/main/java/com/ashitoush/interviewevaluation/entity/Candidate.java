package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "candidates", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "uk_candidate_email"),
        @UniqueConstraint(columnNames = "phone_number", name = "uk_candidate_phoneNumber")
})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_seq_gen")
    @SequenceGenerator(name = "candidate_seq_gen", sequenceName = "candidate_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 100, nullable = false)
    private String email;
}

