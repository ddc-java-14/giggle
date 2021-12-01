package edu.cnm.deepdive.giggle.controller;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.giggle.R;
import edu.cnm.deepdive.giggle.databinding.FragmentSearchBinding;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.viewmodel.JokeViewModel;

public class SearchFragment extends Fragment {

  private MediaPlayer mediaPlayer;
  private FragmentSearchBinding binding;
  private JokeViewModel viewModel;
  private Joke joke;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentSearchBinding.inflate(inflater, container, false);
    binding.filledHeart.setVisibility(View.GONE);
    binding.unfilledHeart.setVisibility(View.GONE);
    binding.search.setOnClickListener(
        (v) -> viewModel.search(binding.searchWord.getText().toString().trim()));


    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(JokeViewModel.class);
    viewModel.getSearchResult().observe(getViewLifecycleOwner(), (joke) -> {
      this.joke = joke;
      binding.joke.setText(joke.getContent());
      mediaPlayer = MediaPlayer.create(getContext(), R.raw.laugh);
      mediaPlayer.start();
      mediaPlayer.setOnCompletionListener((ignored) -> mediaPlayer.release());
      binding.dog.setVisibility(View.VISIBLE);
      if (joke.isFavorite()) {
        Log.d(getClass().getSimpleName(), "favorite");
        binding.filledHeart.setVisibility(View.VISIBLE);

      } else {
        Log.d(getClass().getSimpleName(), "Not a favorite");
        binding.unfilledHeart.setVisibility(View.VISIBLE);

      }
    });
    viewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
        binding.dog.setVisibility(View.INVISIBLE);

      }
    });
    mediaPlayer = MediaPlayer.create(getContext(), R.raw.laugh);
    mediaPlayer.start();
    mediaPlayer.setOnCompletionListener((ignored) -> mediaPlayer.release());
    binding.filledHeart.setOnClickListener(
        (v) -> {
          viewModel.delete(joke);
          binding.unfilledHeart.setVisibility(View.VISIBLE);
          binding.filledHeart.setVisibility(View.GONE);
        }
    );
    binding.unfilledHeart.setOnClickListener(
        (v) -> {
          viewModel.save(joke);
          binding.filledHeart.setVisibility(View.VISIBLE);
          binding.unfilledHeart.setVisibility(View.GONE);
        }
    );



  }
}