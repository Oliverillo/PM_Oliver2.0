package com.example.listasejer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {
    private List<Films> listaPeliculas;
    private Context context;
    public FilmsAdapter(Context context, List<Films> listaPeliculas) {
        this.context = context;
        this.listaPeliculas = listaPeliculas;
    }
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Films film = listaPeliculas.get(position);
        holder.bindFilm(film);
    }
    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }
    public class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.textView);
        }
        public void bindFilm(Films film) {
            titleTextView.setText(film.getTitle());
            int resId = context.getResources().getIdentifier(
                    film.getImage(),
                    "drawable",
                    context.getPackageName()
            );
            imageView.setImageResource(resId);
        }
    }
}
