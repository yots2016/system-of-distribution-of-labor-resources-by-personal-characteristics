package com.distributionsystem.controller;

import com.distributionsystem.model.WeightingFactor;
import com.distributionsystem.service.WeightingFactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/weighting-factor")
@RequiredArgsConstructor
@Controller
public class WeightingFactorController {

    private final WeightingFactorService weightingFactorService;

    @GetMapping
    public String showCommonPersonalDataService(Model model, @RequestParam("page") Optional<Integer> pageNumber,
                                                @RequestParam("size") Optional<Integer> size) {
        int currentPage = pageNumber.orElse(1);
        int pagesSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pagesSize);
        Page<WeightingFactor> weightingFactorPage = weightingFactorService.getAllEmployees(pageable);

        model.addAttribute("weightingFactorPage", weightingFactorPage);

        int totalPages = weightingFactorPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pagesNumbers", pagesNumbers);
        }

        return "weighting-factor";
    }
}
