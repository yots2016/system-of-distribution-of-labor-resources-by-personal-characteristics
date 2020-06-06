package com.distributionsystem.controller;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.Category;
import com.distributionsystem.model.Project;
import com.distributionsystem.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequestMapping({"/projects", "/"})
@RequiredArgsConstructor
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final CategoryService categoryService;
    private final CommonPersonalDataService commonPersonalDataService;
    private final CommonProfessionalDataService commonProfessionalDataService;
    private final WeightingFactorService weightingFactorService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Project> projectPage = projectService.getAllProjects(pageable);

        model.addAttribute("projectPage", projectPage);

        int totalPages = projectPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "projects";
    }


    @GetMapping("/add")
    public String addProject(@Valid Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("newProjectDto", new NewProjectDto());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("commonPersonalDataList", commonPersonalDataService.getAllCommonPersonalData());
        model.addAttribute("commonProfessionalDataList", commonProfessionalDataService.getAllCommonProfessionalData());
        model.addAttribute("weightingFactorList", weightingFactorService.getAllWeightingFactor());

        return "add-project";
    }

    @PostMapping("/save")
    public String save(NewProjectDto newProjectDto) {
        log.info("/ {}", newProjectDto.getName());

        projectService.saveProject(newProjectDto);

        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);

        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException("The project was not found."));
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("project", project);
        model.addAttribute("categoryList", categoryList);

        return "edit-project";
    }

    @GetMapping("/view/{id}")
    public String viewProject(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException("The project was not found."));

        model.addAttribute("employeesPage", project.getEmployeesSet());

        return "view-project";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerException() {
        return "error/404";
    }
}
