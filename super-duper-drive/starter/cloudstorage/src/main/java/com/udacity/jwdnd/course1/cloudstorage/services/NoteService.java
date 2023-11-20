package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public int createNote(Note note, Integer userId) {
        return noteMapper.insertNote(new Note(null, note.getNoteTitle(), note.getNoteDescription(), userId));
    }

    public int updateNote(Note note, Integer userId) {
        return noteMapper.updateNote(new Note(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription(), userId));
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }

    public List<Note> getNotesFor(Integer userId) {
        return noteMapper.getNotesFor(userId);
    }

}
