package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = "projectSet")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = false)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Project> projectSet;
}
