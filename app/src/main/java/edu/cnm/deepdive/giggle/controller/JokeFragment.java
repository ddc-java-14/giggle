package edu.cnm.deepdive.giggle.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.giggle.R;
import edu.cnm.deepdive.giggle.adapter.JokeAdapter;
import edu.cnm.deepdive.giggle.databinding.FragmentJokeBinding;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.adapter.JokeAdapter;
import edu.cnm.deepdive.giggle.viewmodel.JokeViewModel;

public class JokeFragment extends Fragment {

  private FragmentJokeBinding binding;
  private JokeViewModel viewModel;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {

    binding = FragmentJokeBinding.inflate(inflater, container, false);
    binding.addJoke.setOnClickListener((v) -> Navigation
        .findNavController(binding.getRoot())
        .navigate(JokeFragmentDirections.openJoke()));
    return binding.getRoot();

  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(JokeViewModel.class);
    viewModel
        .getJokes()
        .observe(getViewLifecycleOwner(), (notes) -> {
          JokeAdapter adapter = new JokeAdapter(getContext(),jokes);
          binding.jokes.setAdapter(adapter);
        });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

}


