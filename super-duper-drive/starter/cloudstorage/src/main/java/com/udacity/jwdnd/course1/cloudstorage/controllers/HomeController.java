package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import com.udacity.jwdnd.course1.cloudstorage.utils.EncryptionService;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final FileService fileService;

    @GetMapping()
    public String homeView(Model model) {
        User user = getCurrentUser();
        model.addAttribute("files", fileService.getFilesFor(user.getUserId()));
        model.addAttribute("notes", noteService.getNotesFor(user.getUserId()));
        model.addAttribute("credentials", credentialService.getCredentialsFor(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

//    @PostMapping("/notes")
//    public String addAndUpdateNotes(@ModelAttribute("note") Note note, Model model) {
//        User user = getCurrentUser();
//
//        if (note.getNoteId() > 0) {
//            int rowId = noteService.updateNote(note, user.getUserId());
//
//            if (rowId <= 0) {
//                modelError(model, "Note update failed! Please try again");
//            } else {
//                modelSuccess(model, "Note successfully updated");
//            }
//        } else {
//            int rowId = noteService.createNote(note, user.getUserId());
//
//            if (rowId <= 0) {
//                modelError(model, "Note creation failed! Please try again");
//            } else {
//                modelSuccess(model, "Note successfully created");
//            }
//        }
//
//        return "result";
//    }
//
//    @GetMapping("/notes/delete/{noteId}")
//    public String deleteNote(@PathVariable("noteId") Integer noteId, Model model) {
//        if (noteService.deleteNote(noteId) < 0) {
//            modelError(model, "Note deletion failed!");
//        } else {
//            modelSuccess(model, "Note successfully deleted");
//        }
//        return "result";
//    }

//    @PostMapping("/credentials")
//    public String addAndUpdateCredentials(@ModelAttribute("credential") Credential credential, Model model) {
//        User user = getCurrentUser();
//
//        if (credential.getCredentialId() > 0) {
//            int rowId = credentialService.updateCredential(credential, user.getUserId());
//
//            if (rowId <= 0) {
//                modelError(model, "Credential updated failed! Please try again");
//            } else {
//                modelSuccess(model, "Credential successfully updated");
//            }
//        } else {
//            int rowId = credentialService.createCredential(credential, user.getUserId());
//
//            if (rowId <= 0) {
//                modelError(model, "Credential creation failed! Please try again");
//            } else {
//                modelSuccess(model, "Credential successfully created");
//            }
//        }
//
//        return "result";
//    }
//
//    @GetMapping("/credentials/delete/{credentialId}")
//    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Model model) {
//        if (credentialService.deleteCredential(credentialId) < 0) {
//            modelError(model, "Credential deletion failed! Please try again");
//        } else {
//            modelSuccess(model, "Credential successfully deleted");
//        }
//        return "result";
//    }

//    @PostMapping("/files")
//    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
//        User user = getCurrentUser();
//
//        if (file.isEmpty()) {
//            modelError(model, "File is empty!");
//            return "result";
//        }
//        if (fileService.isFileExists(file.getOriginalFilename(), user.getUserId())) {
//            modelError(model, "File already exists!");
//            return "result";
//        }
//
//        try {
//                int rowId = fileService.uploadFile(file, user.getUserId());
//                if (rowId < 0) {
//                    modelError(model, "File upload failed! Please try again");
//                }else {
//                    modelSuccess(model, "File successfully uploaded");
//                }
//            return "result";
//
//        } catch (IOException e) {
//            modelError(model, "File upload failed!");
//            return "result";
//        }
//
//    }
//
//    @GetMapping("/files/view/{fileId}")
//    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable("fileId") Integer fileId, Model model) {
//        modelSuccess(model, null);
//        File file = fileService.getFile(fileId);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(file.getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .body(new ByteArrayResource(file.getFileData()));
//    }
//
//    @GetMapping("/files/delete/{fileId}")
//    public String deleteFile(@PathVariable("fileId") Integer fileId, Model model) {
//        if (fileService.deleteFile(fileId) < 0) {
//            modelError(model, "File deletion failed! Please try again");
//        } else {
//            modelSuccess(model, "File successfully deleted");
//        }
//        return "result";
//    }

    private User getCurrentUser() {
        return userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }

//    private void modelSuccess(Model model, String message) {
//        setupModel(model, message, false);
//    }
//
//    private void modelError(Model model, String message) {
//        setupModel(model, message, true);
//    }

//    private void setupModel(Model model, String message, boolean error) {
//        if (message != null) {
//            if (error) {
//                model.addAttribute("alertError", true);
//            } else {
//                model.addAttribute("alertSuccess", true);
//            }
//            model.addAttribute("alertMessage", message);
//        }
//
//        User user = getCurrentUser();
//        model.addAttribute("files", fileService.getFilesFor(user.getUserId()));
//        model.addAttribute("notes", noteService.getNotesFor(user.getUserId()));
//        model.addAttribute("credentials", credentialService.getCredentialsFor(user.getUserId()));
//        model.addAttribute("encryptionService", encryptionService);
//    }
}
