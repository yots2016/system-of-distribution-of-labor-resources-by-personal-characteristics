package com.distributionsystem.controller;

import com.distributionsystem.model.WeightingFactor;
import com.distributionsystem.service.WeightingFactorService;
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

@RequestMapping("/weighting-factor")
@RequiredArgsConstructor
@Controller
public class WeightingFactorController {

    private final WeightingFactorService weightingFactorService;

    private int currentPage = 1;
    private int pagesSize = 10;

    @RequestMapping("")
    public String showCommonPersonalDataService(Model model, @RequestParam("page") Optional<Integer> pageNumber,
                                                @RequestParam("page") Optional<Integer> size) {
        //TODO 30.05.2020 Refactor with Optional::orElse()
        pageNumber.ifPresent(number -> currentPage = number);
        size.ifPresent(sizeNumber -> pagesSize = sizeNumber);

        Pageable pageable = PageRequest.of(currentPage - 1, pagesSize);
        Page<WeightingFactor> cPDPage = weightingFactorService.getAllEmployees(pageable);

        model.addAttribute("CPDPage", cPDPage);

        int totalPages = cPDPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(0, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pagesNumbers", pagesNumbers);
        }

        return "weighting-factor";
    }
}
