package com.ahsan.roomvirewmodellivedatarecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NoteViewModel noteViewModel;

    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    public static final int ADD_NOTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNote;

        recyclerView = findViewById(R.id.noteRecyclerview);
        addNote = findViewById(R.id.addNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,AddNewNoteActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);

            }
        });

        noteAdapter = new NoteAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update recyelerview
                noteAdapter.setNotes(notes);
                Log.e("List", notes.toString() );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
           if (data != null){
               String title = data.getStringExtra(AddNewNoteActivity.constant_Add_Title);
               String description = data.getStringExtra(AddNewNoteActivity.constant_Add_Description);
               int priority = data.getIntExtra(AddNewNoteActivity.constant_Add_priority,1);

               Note note = new Note(title,description,priority);
               noteViewModel.insert(note);

               Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(this, "Note not Saved", Toast.LENGTH_SHORT).show();
           }

        }
    }
}
