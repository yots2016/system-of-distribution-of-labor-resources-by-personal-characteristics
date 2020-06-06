package com.distributionsystem.controller;

import com.distributionsystem.model.CommonProfessionalData;
import com.distributionsystem.service.CommonProfessionalDataService;
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

@RequestMapping("/common-prof-data")
@RequiredArgsConstructor
@Controller
public class CommonProfessionalDataController {

    private final CommonProfessionalDataService commonProfessionalDataService;

    private int currentPage = 1;
    private int pagesSize = 10;

    @RequestMapping("")
    public String showCommonProfessionalDataService(Model model, @RequestParam("page") Optional<Integer> pageNumber,
                                                @RequestParam("page") Optional<Integer> size) {
        //TODO 30.05.2020 Refactor with Optional::orElse()
        pageNumber.ifPresent(number -> currentPage = number);
        size.ifPresent(sizeNumber -> pagesSize = sizeNumber);

        Pageable pageable = PageRequest.of(currentPage - 1, pagesSize);
        Page<CommonProfessionalData> cPDPage = commonProfessionalDataService.getAllEmployees(pageable);

        model.addAttribute("CPDPage", cPDPage);

        int totalPages = cPDPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(0, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pagesNumbers", pagesNumbers);
        }

        return "common-prof-data";
    }
}
