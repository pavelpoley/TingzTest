package pavelpoley.tingz.ui.movielist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pavelpoley.tingz.R;
import pavelpoley.tingz.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private List<Movie> list = new ArrayList<>();
    private Callback callback;

    interface Callback{
        void onItemClick(Movie movie);
    }

    MovieAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = list.get(position);

        Picasso.get().load(movie.getImage())
                .resize(80,80)
                .into(holder.imgMovie);

        holder.tvTitle.setText(movie.getTitle());
        holder.root.setOnClickListener(view -> callback.onItemClick(movie));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void setMovies(List<Movie> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView imgMovie;
        private TextView tvTitle;
        private CardView root;

        ViewHolder(@NonNull View view) {
            super(view);
            imgMovie = view.findViewById(R.id.img_movie);
            tvTitle =  view.findViewById(R.id.tv_movie_title);
            root = view.findViewById(R.id.card_root);
        }
    }
}
