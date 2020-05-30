package com.batuhaniskr.project.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
