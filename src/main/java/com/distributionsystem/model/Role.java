package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "\"role\"")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
