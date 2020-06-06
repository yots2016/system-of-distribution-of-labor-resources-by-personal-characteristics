package com.distributionsystem.controller;

import com.distributionsystem.dto.NewEmployeeDto;
import com.distributionsystem.model.Employee;
import com.distributionsystem.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/employees")
@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CommonPersonalDataService commonPersonalDataService;
    private final CommonProfessionalDataService commonProfessionalDataService;
    private final WeightingFactorService weightingFactorService;

    @RequestMapping("")
    public String showEmployees(Model model, @RequestParam("page") Optional<Integer> pageNumber,
                                @RequestParam("size") Optional<Integer> size) {
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

        return "employees";
    }

    @RequestMapping(value = "/add")
    public String addProject(@Valid Model model) {
        model.addAttribute("newEmployeeDto", new NewEmployeeDto());
        model.addAttribute("commonPersonalDataList", commonPersonalDataService.getAllCommonPersonalData());
        model.addAttribute("commonProfessionalDataList", commonProfessionalDataService.getAllCommonProfessionalData());
        model.addAttribute("weightingFactorList", weightingFactorService.getAllWeightingFactor());

        return "add-employee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(NewEmployeeDto newEmployeeDto) {
        employeeService.saveEmployee(newEmployeeDto);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        return "redirect:/employees";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }
}
