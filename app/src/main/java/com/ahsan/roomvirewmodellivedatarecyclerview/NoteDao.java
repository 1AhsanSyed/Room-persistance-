package com.ahsan.roomvirewmodellivedatarecyclerview;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void Update(Note note);

    @Delete
    void delete(Note note);

    // for delete all data
    @Query("Delete From note_table")
    void deleteAll();


    // to get all the data from database
    //we use liveData here to Update the UI
    @Query("Select * From note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNoteData();

}
