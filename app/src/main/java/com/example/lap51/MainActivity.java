package com.example.lap51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToAddNote;
    private RecyclerView rvMainNotes;
    private TextView tvEmptyMainNotes;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRecyclerView();
        setupEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (noteAdapter != null) {
            noteAdapter.notifyDataSetChanged();
        }
        updateEmptyState();
    }

    private void initViews() {
        btnGoToAddNote = findViewById(R.id.btnGoToAddNote);
        rvMainNotes = findViewById(R.id.rvMainNotes);
        tvEmptyMainNotes = findViewById(R.id.tvEmptyMainNotes);
    }

    private void setupRecyclerView() {
        noteList = NoteRepository.getInstance().getNotes();
        noteAdapter = new NoteAdapter(noteList);
        rvMainNotes.setLayoutManager(new LinearLayoutManager(this));
        rvMainNotes.setAdapter(noteAdapter);
        updateEmptyState();
    }

    private void setupEvents() {
        // Event-driven programming: OnClickListener to navigate to AddNoteActivity
        btnGoToAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });
    }

    private void updateEmptyState() {
        if (noteList.isEmpty()) {
            tvEmptyMainNotes.setVisibility(View.VISIBLE);
            rvMainNotes.setVisibility(View.GONE);
        } else {
            tvEmptyMainNotes.setVisibility(View.GONE);
            rvMainNotes.setVisibility(View.VISIBLE);
        }
    }
}
