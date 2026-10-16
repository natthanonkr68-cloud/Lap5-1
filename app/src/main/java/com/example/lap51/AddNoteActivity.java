package com.example.lap51;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etUserName, etUserEmail, etNoteTitle, etNoteContent, etCheckListItems;
    private RadioGroup rgNoteType;
    private RadioButton rbTextNote;
    private Button btnAddNote, btnBack;
    private RecyclerView rvAddedNotes;
    private TextView tvEmptyNotes;

    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        initViews();
        setupRecyclerView();
        setupEvents();
        updateNotesListVisibility();
    }

    private void initViews() {
        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        etCheckListItems = findViewById(R.id.etCheckListItems);

        rgNoteType = findViewById(R.id.rgNoteType);
        rbTextNote = findViewById(R.id.rbTextNote);

        btnAddNote = findViewById(R.id.btnAddNote);
        btnBack = findViewById(R.id.btnBack);

        rvAddedNotes = findViewById(R.id.rvAddedNotes);
        tvEmptyNotes = findViewById(R.id.tvEmptyNotes);
    }

    private void setupRecyclerView() {
        noteList = NoteRepository.getInstance().getNotes();
        noteAdapter = new NoteAdapter(noteList);
        rvAddedNotes.setLayoutManager(new LinearLayoutManager(this));
        rvAddedNotes.setAdapter(noteAdapter);
    }

    private void setupEvents() {
        // Event-driven: Toggle input views when RadioButton changes
        rgNoteType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbTextNote) {
                etNoteContent.setVisibility(View.VISIBLE);
                etCheckListItems.setVisibility(View.GONE);
            } else {
                etNoteContent.setVisibility(View.GONE);
                etCheckListItems.setVisibility(View.VISIBLE);
            }
        });

        // Event-driven: Button click listener to add a new Note
        btnAddNote.setOnClickListener(v -> addNote());

        // Event-driven: Button click listener to navigate back
        btnBack.setOnClickListener(v -> finish());
    }

    private void addNote() {
        String userName = etUserName.getText().toString().trim();
        String userEmail = etUserEmail.getText().toString().trim();
        String title = etNoteTitle.getText().toString().trim();

        if (userName.isEmpty()) {
            etUserName.setError("กรุณากรอกชื่อผู้ใช้");
            etUserName.requestFocus();
            return;
        }

        if (title.isEmpty()) {
            etNoteTitle.setError("กรุณากรอกหัวข้อโน๊ต");
            etNoteTitle.requestFocus();
            return;
        }

        // Create User object (Requirement 3)
        User user = new User(userName, userEmail);
        Note newNote;

        if (rbTextNote.isChecked()) {
            String content = etNoteContent.getText().toString().trim();
            if (content.isEmpty()) {
                etNoteContent.setError("กรุณากรอกเนื้อหาโน๊ต");
                etNoteContent.requestFocus();
                return;
            }
            newNote = new TextNote(title, user, content);
        } else {
            String itemsText = etCheckListItems.getText().toString().trim();
            if (itemsText.isEmpty()) {
                etCheckListItems.setError("กรุณากรอกรายการเช็คลิสต์");
                etCheckListItems.requestFocus();
                return;
            }
            String[] splitItems = itemsText.split("\n");
            List<String> itemsList = new ArrayList<>(Arrays.asList(splitItems));
            newNote = new CheckListNote(title, user, itemsList);
        }

        // Save note to Repository
        NoteRepository.getInstance().addNote(newNote);

        // Update UI
        noteAdapter.notifyItemInserted(noteList.size() - 1);
        updateNotesListVisibility();

        // Clear input form for next note
        etNoteTitle.setText("");
        etNoteContent.setText("");
        etCheckListItems.setText("");

        Toast.makeText(this, "เพิ่มโน๊ตสำเร็จ! (บันทึกข้อมูลโดย " + user.getName() + ")", Toast.LENGTH_SHORT).show();
    }

    private void updateNotesListVisibility() {
        if (noteList.isEmpty()) {
            tvEmptyNotes.setVisibility(View.VISIBLE);
            rvAddedNotes.setVisibility(View.GONE);
        } else {
            tvEmptyNotes.setVisibility(View.GONE);
            rvAddedNotes.setVisibility(View.VISIBLE);
        }
    }
}
