package com.zero.payroll.management.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "m_group")
public class MPosition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "basic_salary")
    private Double basicSalary;

    @Column(name = "allowance")
    private Double allowance;

    public static final String FIELD_NAME = "name";
}
