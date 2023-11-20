package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
        User user = getCurrentUser();

        if (file.isEmpty()) {
            model.addAttribute("error", "File is empty!");
            return "result";
        }
        if (fileService.isFileExists(file.getOriginalFilename(), user.getUserId())) {
            model.addAttribute("error", "File already exists!");
            return "result";
        }

        try {
            int rowId = fileService.uploadFile(file, user.getUserId());
            if (rowId < 0) {
                model.addAttribute("error", "File upload failed! Please try again");
            }else {
                model.addAttribute("success", "File successfully uploaded");
            }
            return "result";

        } catch (IOException e) {
            model.addAttribute("error", "File upload failed!");
            return "result";
        }

    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable("fileId") Integer fileId) {
        File file = fileService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId, Model model) {
        if (fileService.deleteFile(fileId) < 0) {
            model.addAttribute("error", "File deletion failed! Please try again");
        } else {
            model.addAttribute("success", "File successfully deleted");
        }
        return "result";
    }


    public User getCurrentUser() {
        return userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
