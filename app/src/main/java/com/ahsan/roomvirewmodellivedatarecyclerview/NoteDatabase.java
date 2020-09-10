package com.ahsan.roomvirewmodellivedatarecyclerview;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //turn class to singleton class
    private static  NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .addCallback(roomDatabaseCallBack)
                    .fallbackToDestructiveMigration().build();
        }

        return instance;
    }
    private static RoomDatabase.Callback roomDatabaseCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new pupolateDBAsyncTask(instance).execute();
        }
    };

    private static class pupolateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private pupolateDBAsyncTask(NoteDatabase noteDatabase) {
            noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note( "title1","description 1",1));
            noteDao.insert(new Note( "title1","description 2",2));
            return null;
        }
    }
}
