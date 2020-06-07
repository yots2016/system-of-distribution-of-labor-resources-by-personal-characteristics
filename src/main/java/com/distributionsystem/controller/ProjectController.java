package com.distributionsystem.controller;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.*;
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

    private static final String THE_PROJECT_WAS_NOT_FOUND = "The project was not found.";
    private static final String THE_EMPLOYEE_WAS_NOT_FOUND = "The employee was not found.";

    private final ProjectService projectService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;
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

    @GetMapping("view/delete/employee")
    public String deleteEmployeeFromProject(@RequestParam("project-id") Long projectId,
                                            @RequestParam("employee-id") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId)
                .orElseThrow(() -> new RuntimeException(THE_EMPLOYEE_WAS_NOT_FOUND));
        employee.setProject(null);
        employeeService.saveEmployee(employee);

        return String.format("redirect:/view/%s", projectId);
    }

    @GetMapping("view/change/select/employee")
    public String selectEmployeeInProject(@RequestParam("project-id") Long projectId,
                                          @RequestParam("employee-id") Long employeeId,
                                          @RequestParam("new-employee-id") Long newEmployeeId) {
        Project project = projectService.getProjectById(projectId)
                .orElseThrow(() -> new RuntimeException(THE_PROJECT_WAS_NOT_FOUND));
        Employee employee = employeeService.getEmployeeById(employeeId)
                .orElseThrow(() -> new RuntimeException(THE_EMPLOYEE_WAS_NOT_FOUND));
        ProjectEmployeeRole projectEmployeeRole = employee.getProjectEmployeeRole();
        employee.setProjectEmployeeRole(null);
        employee.setProject(null);
        employeeService.saveEmployee(employee);
        Employee newEmployee = employeeService.getEmployeeById(newEmployeeId)
                .orElseThrow(() -> new RuntimeException(THE_EMPLOYEE_WAS_NOT_FOUND));
        newEmployee.setProject(project);
        newEmployee.setProjectEmployeeRole(projectEmployeeRole);
        employeeService.saveEmployee(newEmployee);

        return String.format("redirect:/view/%s", projectId);
    }

    @GetMapping("view/change/employee")
    public String changeEmployeeInProject(@RequestParam("page") Optional<Integer> pageNumber,
                                          @RequestParam("size") Optional<Integer> size,
                                          @RequestParam("project-id") Long projectId,
                                          @RequestParam("employee-id") Long employeeId,
                                          Model model) {
        int currentPage = pageNumber.orElse(1);
        int pagesSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pagesSize);
        Page<Employee> employeesPage = employeeService.getAllEmployees(pageable);

        model.addAttribute("employeesPage", employeesPage);

        int totalPages = employeesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pagesNumbers", pagesNumbers);
        }

        model.addAttribute("projectId", projectId);
        model.addAttribute("employeeId", employeeId);

        return "select-new-employee";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException(THE_PROJECT_WAS_NOT_FOUND));
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("project", project);
        model.addAttribute("categoryList", categoryList);

        return "edit-project";
    }

    @GetMapping("/view/{id}")
    public String viewProject(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException(THE_PROJECT_WAS_NOT_FOUND));

        model.addAttribute("project", project);

        return "view-project";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerException() {
        return "error/404";
    }
}
