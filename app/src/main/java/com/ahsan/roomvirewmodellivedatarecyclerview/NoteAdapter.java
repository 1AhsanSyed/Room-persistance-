package com.ahsan.roomvirewmodellivedatarecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private List<Note> myList = new ArrayList<>();
/*
    public NoteAdapter(Context context, List<Note> myList) {
        this.context = context;
        this.myList = myList;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = myList.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.priority.setText(String.valueOf(note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setNotes (List<Note> noteList){
        this.myList=noteList;
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,description,priority;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
