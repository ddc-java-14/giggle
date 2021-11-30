package edu.cnm.deepdive.giggle.controller;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.giggle.R;
import edu.cnm.deepdive.giggle.databinding.FragmentSearchBinding;
import edu.cnm.deepdive.giggle.viewmodel.JokeViewModel;

public class SearchFragment extends Fragment {

  private MediaPlayer mediaPlayer;
  private FragmentSearchBinding binding;
  private JokeViewModel viewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentSearchBinding.inflate(inflater, container, false);
    binding.search.setOnClickListener((v) -> viewModel.search(binding.searchWord.getText().toString().trim()));
    // TODO Set up event listeners on controls.
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(JokeViewModel.class);
    viewModel.getSearchResult().observe(getViewLifecycleOwner(), (joke) ->
        binding.joke.setText(joke.getContent()));
    mediaPlayer = MediaPlayer.create(getContext(), R.raw.laugh);
    mediaPlayer.start();
    mediaPlayer.setOnCompletionListener((ignored) -> mediaPlayer.release());

  }
}