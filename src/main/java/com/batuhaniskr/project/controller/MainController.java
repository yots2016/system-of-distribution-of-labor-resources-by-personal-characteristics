package com.batuhaniskr.project.controller;

import com.batuhaniskr.project.model.Category;
import com.batuhaniskr.project.model.Project;
import com.batuhaniskr.project.service.CategoryService;
import com.batuhaniskr.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/projects")
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    private ProjectService projectService;
    private CategoryService categoryService;

    private static int currentPage = 1;
    private static int pageSize = 5;

    @Autowired
    public MainController(ProjectService projectService, CategoryService categoryService) {
        this.projectService = projectService;
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);

        Pageable pageable = new PageRequest(currentPage - 1, pageSize);
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


    @RequestMapping(value = "/add")
    public String addProject(@Valid Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("project", new Project());
        model.addAttribute("categoryList", categoryList);

        return "addproject";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Project project) {
        LOG.log(Level.INFO, "/ " + project.getName());
        projectService.saveProject(project);

        return "redirect:/projects";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProject(id);

        return "redirect:/projects";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editProject(@PathVariable("id") Integer id, Model model) {
        Project project = projectService.getProjectById(id);
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("project", project);
        model.addAttribute("categoryList", categoryList);

        return "editproject";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerException() {
        return "error/404";
    }
}