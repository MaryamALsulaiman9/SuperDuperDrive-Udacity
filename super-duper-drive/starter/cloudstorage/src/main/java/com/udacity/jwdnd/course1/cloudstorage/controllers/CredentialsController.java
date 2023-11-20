package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/credentials")
public class CredentialsController {

    private final UserService userService;
    private final CredentialService credentialService;

    @PostMapping("/add-update")
    public String addAndUpdateCredentials(@ModelAttribute("credential") Credential credential, Model model) {
        User user = getCurrentUser();

        if (credential.getCredentialId() == null){
            int rowId = credentialService.createCredential(credential, user.getUserId());

            if (rowId <= 0) {
                model.addAttribute("error", "Credential creation failed! Please try again");
            } else {
                model.addAttribute("success", "Credential successfully created");
            }
        }else {
                int rowId = credentialService.updateCredential(credential, user.getUserId());

                if (rowId <= 0) {
                    model.addAttribute("error", "Credential updated failed! Please try again");
                } else {
                    model.addAttribute("success", "Credential successfully updated");
                }
            }
        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Model model) {
        if (credentialService.deleteCredential(credentialId) < 0) {
            model.addAttribute("error", "Credential deletion failed! Please try again");
        } else {
            model.addAttribute("success", "Credential successfully deleted");
        }
        return "result";
    }

    public User getCurrentUser() {
        return userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
