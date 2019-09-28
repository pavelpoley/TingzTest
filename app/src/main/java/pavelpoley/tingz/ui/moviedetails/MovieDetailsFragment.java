package pavelpoley.tingz.ui.moviedetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pavelpoley.tingz.R;
import pavelpoley.tingz.model.Movie;
import pavelpoley.tingz.ui.MainActivity;

public class MovieDetailsFragment extends Fragment {


    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_RATING = "ARG_RATING";
    private static final String ARG_YEAR = "ARG_YEAR";
    private static final String ARG_GENRE = "ARG_GENRE";
    private static final String ARG_IMG = "arg_img";

    public static MovieDetailsFragment newInstance(Movie movie) {

        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, movie.getTitle());
        bundle.putString(ARG_IMG, movie.getImage());
        bundle.putString(ARG_RATING, String.valueOf(movie.getRating()));
        bundle.putString(ARG_YEAR, String.valueOf(movie.getReleaseYear()));
        bundle.putStringArrayList(ARG_GENRE, (ArrayList<String>) movie.getGenre());

        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(bundle);

        return movieDetailsFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (getActivity() instanceof MainActivity) {
            getActivity().setTitle(R.string.title_movie_details);
            ((MainActivity) getActivity()).showBackArrow();
        }

        ImageView imgMovie = view.findViewById(R.id.img_movie);
        TextView tvTitle = view.findViewById(R.id.tv_movie_title);
        TextView tvRating = view.findViewById(R.id.tv_rating);
        TextView tvYear = view.findViewById(R.id.tv_year);
        TextView tvGenre = view.findViewById(R.id.tv_genre);

        Bundle arguments = getArguments();

        if (arguments != null) {
            tvTitle.setText(arguments.getString(ARG_TITLE));
            tvRating.setText(arguments.getString(ARG_RATING));
            tvYear.setText(arguments.getString(ARG_YEAR));
            ArrayList<String> stringArrayList = arguments.getStringArrayList(ARG_GENRE);
            if (stringArrayList != null) {
                tvGenre.setText(stringArrayList.toString());
            }

            Picasso.get().load(arguments.getString(ARG_IMG)).into(imgMovie);
        }
    }


}
