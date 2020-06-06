package com.distributionsystem.controller;

import com.distributionsystem.model.CommonPersonalData;
import com.distributionsystem.service.CommonPersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/common-personal-data")
@RequiredArgsConstructor
@Controller
public class CommonPersonalDataController {

    private final CommonPersonalDataService commonPersonalDataService;

    @GetMapping
    public String showCommonPersonalDataService(Model model, @RequestParam("page") Optional<Integer> pageNumber,
                                                @RequestParam("size") Optional<Integer> size) {
        int currentPage = pageNumber.orElse(1);
        int pagesSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pagesSize);
        Page<CommonPersonalData> commonPersonalDataPage = commonPersonalDataService.getAllCommonPersonalData(pageable);

        model.addAttribute("commonPersonalDataPage", commonPersonalDataPage);

        int totalPages = commonPersonalDataPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pagesNumbers", pagesNumbers);
        }

        return "common-personal-data";
    }
}
