package com.batuhaniskr.project.controller;

import com.batuhaniskr.project.model.Employee;
import com.batuhaniskr.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/employees")
@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    private int currentPage = 1;
    private int pagesSize = 10;

    @RequestMapping("")
    public String showEmployees(Model model, @RequestParam("page") Optional<Integer> pageNumber,
                                @RequestParam("page") Optional<Integer> size) {
        //TODO 30.05.2020 Refactor with Optional::orElse()
        pageNumber.ifPresent(number -> currentPage = number);
        size.ifPresent(sizeNumber -> pagesSize = sizeNumber);

        Pageable pageable = new PageRequest(currentPage - 1, pagesSize);
        Page<Employee> employeesPage = employeeService.getAllEmployees(pageable);

        model.addAttribute("employeesPage", employeesPage);

        int totalPages = employeesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(0, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pagesNumbers", pagesNumbers);
        }

        return "employees";
    }
}
