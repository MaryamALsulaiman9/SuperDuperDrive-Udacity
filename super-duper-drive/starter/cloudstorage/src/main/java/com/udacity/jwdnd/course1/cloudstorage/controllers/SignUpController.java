package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        String errorMsg = null;

        if (userService.isUserExists(user.getUsername())) {
            errorMsg = "The username " + user.getUsername() + " already exists.";
        }

        if (errorMsg == null) {
            int rowId = userService.createUser(user);
            if (rowId < 0) {
                errorMsg = "SignUp failed! Please try again";
            }
        }

        if (errorMsg == null) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", errorMsg);
        }

        return "signup";
    }

}
