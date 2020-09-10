package com.ahsan.roomvirewmodellivedatarecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNewNoteActivity extends AppCompatActivity {

    EditText editText_title;
    EditText editText_description;
    NumberPicker numberPickerPriority;

    public static final String constant_Add_Title ="Add_Title";
    public static final String constant_Add_Description ="Add_Description";
    public static final String constant_Add_priority ="Add_priority";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        editText_title = findViewById(R.id.addTitle);
        editText_description = findViewById(R.id.addDescription);
        numberPickerPriority = findViewById(R.id.numberPickerPriority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(5);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.floating_button);
        setTitle("Add Note");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveNote:
                saveNote();
                return true;
             default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = editText_title.getText().toString();
        String description = editText_description.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty() ){
            Toast.makeText(this, "Enter detail to add new note", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(constant_Add_Title,title);
        intent.putExtra(constant_Add_Description,description);
        intent.putExtra(constant_Add_priority,priority);

        setResult(RESULT_OK,intent);
        finish();

    }
}
