package com.ashitoush.interviewevaluation.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "position", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "uk_position_name")
})
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq_gen")
    @SequenceGenerator(name = "position_seq_gen", sequenceName = "position_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
