package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    @PostMapping("/add-update")
    public String addAndUpdateNote(@ModelAttribute("note") Note note, Model model) {
        User user = getCurrentUser();

        if (note.getNoteId() == null){
            int rowId = noteService.createNote(note, user.getUserId());

            if (rowId <= 0) {
                model.addAttribute("error", "Note creation failed! Please try again");
            } else {
                model.addAttribute("success", "Note successfully created");
            }
        } else {
            int rowId = noteService.updateNote(note, user.getUserId());

            if (rowId <= 0) {
                model.addAttribute("error","Note update failed! Please try again");
            } else {
               model.addAttribute("success", "Note successfully updated");
            }
        }

        return "result";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable(name = "noteId") Integer noteId, Model model) {
        if (noteService.deleteNote(noteId) < 0) {
            model.addAttribute("error", "Note deletion failed!");
        } else {
            model.addAttribute("success", "Note successfully deleted");
        }
        return "result";
    }

    private User getCurrentUser() {
        return userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }



}
