package com.example.todolistserver.controller;

import com.example.todolistserver.dao.NoteDao;
import com.example.todolistserver.note.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NotesController {
    private final NoteDao noteDao;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Note> findAllNotes() {
        return noteDao.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")

    public Optional<Note> findNoteById(@PathVariable Long id) {
        return noteDao.findById(id);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")

    public void createNote(@RequestBody Note note) {
        noteDao.save(note);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteNoteById(@PathVariable Long id) {
        noteDao.deleteById(id);
    }
}
