package com.example.todolistserver.dao;

import com.example.todolistserver.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<Note, Long> {
}
