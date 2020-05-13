package com.batuhaniskr.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = false)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projectSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Project> getProjects() {
        return projectSet;
    }

    public void setProject(Project project) {
        //TODO 14.05.2020 Check why "if" statement is here
        if (projectSet.isEmpty()) {
            projectSet = new HashSet<>();
        }
        projectSet.add(project);
    }
}
