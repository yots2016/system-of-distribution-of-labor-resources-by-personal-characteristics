//package com.batuhaniskr.project;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//public class ProjectServiceTests {
//
//    @Mock
//    private ProjectRepository projectRepository;
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    @Test
//    public void getProjectById() {
//        ProjectService projectService = new ProjectService(projectRepository, categoryRepository);
//        Project project = new Project();
//        project.setId(1L);
//        project.setName("Test");
//        project.setPrice(100F);
//        project.setQuantity(3);
//        Category category = new Category();
//        category.setCategoryName("TestCategory");
//        project.setCategory(category);
//
//        projectRepository.save(project);
//
//        when(projectRepository.findOne(1L)).thenReturn(project);
//
//        Project project1 = projectService.getProjectById(1L);
//        assertThat(project1).isEqualTo(project);
//    }
//}
