package com.ahsan.roomvirewmodellivedatarecyclerview;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> getAllNotes;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        //abstract method noteDao()
        //  we can't call abstract method but we call here with thw reference of Database class (NoteDatabase)
        noteDao = noteDatabase.noteDao();
        getAllNotes = noteDao.getAllNoteData();
    }

    public void insert(Note note){
        new insertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new updateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new deleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getGetAllNotes() {
        return getAllNotes;
    }



    private static class  insertAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public insertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class  updateAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public updateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Update(notes[0]);
            return null;
        }
    }


    private static class  deleteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public deleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private static class  deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public  deleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }



}
