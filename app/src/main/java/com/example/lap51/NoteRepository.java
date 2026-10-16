package com.example.lap51;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    private static NoteRepository instance;
    private final List<Note> noteList;

    private NoteRepository() {
        noteList = new ArrayList<>();
    }

    public static synchronized NoteRepository getInstance() {
        if (instance == null) {
            instance = new NoteRepository();
        }
        return instance;
    }

    public List<Note> getNotes() {
        return noteList;
    }

    public void addNote(Note note) {
        noteList.add(note);
    }
}
