package com.distributionsystem;
/*
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProjectRepository projectRepository;

    //@Test
    public void testFindByProjectName() {

        Project project = new Project();

        project.setId(5);
        project.setCategory("testCategory");
        project.setName("testName");
        project.setQuantity(3);
        project.setPrice(12f);

        projectRepository.save(project);

        Project findByProjectName = projectRepository.findOne(project.getId());

        assertThat(findByProjectName).hasFieldOrPropertyWithValue("name", "testName");
    }
} */
