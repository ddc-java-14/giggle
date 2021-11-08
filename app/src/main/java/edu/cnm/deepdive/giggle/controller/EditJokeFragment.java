package edu.cnm.deepdive.giggle.controller;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.giggle.databinding.FragmentEditJokeBinding;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import edu.cnm.deepdive.giggle.viewmodel.JokeViewModel;

public class EditJokeFragment extends BottomSheetDialogFragment implements TextWatcher {

  private FragmentEditJokeBinding binding;
  private JokeViewModel viewModel;
  private long jokeId;
  private Joke joke;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EditJokeFragmentArgs args = EditJokeFragmentArgs.fromBundle(getArguments());
    jokeId = args.getJokeId();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentEditJokeBinding.inflate(inflater, container, false);
    binding.searchWord.addTextChangedListener(this);
    binding.content.addTextChangedListener(this);
    binding.cancel.setOnClickListener((v) -> dismiss());
    binding.save.setOnClickListener((v) -> {
      joke.setSearchWord(binding.searchWord.getText().toString().trim());
      joke.setContent(binding.content.getText().toString().trim());
      viewModel.save(joke);
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(JokeViewModel.class);
    if (jokeId != 0) {
      //TODO Set noteId in viewModel and observe viewModel.getNote().
      viewModel.setJokeId(jokeId);
      viewModel.getJoke().observe(getViewLifecycleOwner(), new Observer<Joke>() {
        @Override
        public void onChanged(Joke joke) {
          viewModel.save(joke);
        }
      });

    } else {
      joke = new Joke();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    // Do nothing
  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    // Do nothing
  }

  @Override
  public void afterTextChanged(Editable editable) {
    checkSubmitConditions();

  }

  @SuppressWarnings("ConstantConditions")
  private void checkSubmitConditions() {
    String searchWord = binding.searchWord
        .getText()
        .toString()
        .trim();
    String content = binding.content
        .getText()
        .toString()
        .trim();
    binding.save.setEnabled(!searchWord.isEmpty() && !content.isEmpty());
  }
}

