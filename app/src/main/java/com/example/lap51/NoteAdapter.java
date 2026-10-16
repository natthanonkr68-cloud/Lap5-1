package com.example.lap51;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvType.setText(note.getNoteType());

        if (note.getUser() != null) {
            holder.tvUser.setText("👤 ผู้เขียน: " + note.getUser().getUserInfo());
        } else {
            holder.tvUser.setText("👤 ผู้เขียน: ไม่ระบุ");
        }

        holder.tvDetails.setText(note.getDetails());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvType, tvUser, tvDetails;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvType = itemView.findViewById(R.id.tvNoteType);
            tvUser = itemView.findViewById(R.id.tvUserInfo);
            tvDetails = itemView.findViewById(R.id.tvNoteDetails);
        }
    }
}
