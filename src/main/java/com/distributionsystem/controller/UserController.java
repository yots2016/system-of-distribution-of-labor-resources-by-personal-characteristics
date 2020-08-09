package com.distributionsystem.controller;

import com.distributionsystem.dto.UserRegistrationDto;
import com.distributionsystem.model.User;
import com.distributionsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        return "login";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, Model model) {
        Optional<User> user = userService.findByEmail(userDto.getEmail());
        user.ifPresent(existingUser -> result.rejectValue("email", "", "There is already an account registered with that email"));
        if (result.hasErrors()) {
            return "login";
        }
        userService.save(userDto);
        return "redirect:/login?success";
    }
}