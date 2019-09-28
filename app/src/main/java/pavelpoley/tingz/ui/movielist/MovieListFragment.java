package pavelpoley.tingz.ui.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pavelpoley.tingz.R;
import pavelpoley.tingz.model.Movie;
import pavelpoley.tingz.system.Navigation;
import pavelpoley.tingz.ui.MainActivity;
import pavelpoley.tingz.ui.moviedetails.MovieDetailsFragment;

public class MovieListFragment extends Fragment implements MovieAdapter.Callback {

    private static final String TAG = "MovieListFragment";

    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        if (savedInstanceState == null) {
            movieListViewModel.loadMovies().observe(this, this::updateList);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (getActivity() instanceof MainActivity) {
            getActivity().setTitle(R.string.title_movies);
            ((MainActivity) getActivity()).hideBackArrow();
        }

        recyclerView = view.findViewById(R.id.rv_movies);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateList(movieListViewModel.getMoviesLiveData().getValue());
    }

    private void updateList(List<Movie> movies) {

        if (movies == null)
            return;

        movieAdapter.setMovies(movies);
    }

    @Override
    public void onItemClick(Movie movie) {
        MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.newInstance(movie);
        Navigation.replaceFragment(getFragmentManager(), movieDetailsFragment, true);
    }

}
