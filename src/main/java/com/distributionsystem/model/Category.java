package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "category")
    private Set<Project> projectSet;

    public void addProject(Project project) {
        if (projectSet == null) {
            projectSet = new HashSet<>();
        }
        projectSet.add(project);
    }
}
