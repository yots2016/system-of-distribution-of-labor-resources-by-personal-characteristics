package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(exclude = {"category", "employeesSet", "projectEmployeeRoleSet"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "price")
    private Float price;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "project")
    private Set<Employee> employeesSet = new HashSet<>();

    public void addEmployee (Employee employee) {
        this.employeesSet.add(employee);
    }

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectEmployeeRole> projectEmployeeRoleSet = new HashSet<>();

    public void addProjectEmployeeRole(ProjectEmployeeRole projectEmployeeRole) {
        projectEmployeeRoleSet.add(projectEmployeeRole);
    }

    @PrePersist
    public void onPrePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = new Date();
    }
}
