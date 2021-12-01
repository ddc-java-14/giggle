package edu.cnm.deepdive.giggle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.giggle.databinding.ItemJokeBinding;
import edu.cnm.deepdive.giggle.model.entity.Joke;
import java.text.DateFormat;
import java.util.List;


public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.Holder> {

  private final LayoutInflater inflater;
  private final DateFormat dateFormat;
  private final List<Joke> jokes;
  private final RemoveJokeListener listener;

  public JokeAdapter(Context context, List<Joke> jokes,
      RemoveJokeListener listener) {
    inflater = LayoutInflater.from(context);
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    this.jokes = jokes;
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new Holder(ItemJokeBinding.inflate(inflater, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return jokes.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemJokeBinding binding;

    private Holder(@NonNull ItemJokeBinding binding) {
      super(binding.getRoot());
      this.binding = binding;

    }

    private void bind(int position) {
      Joke joke = jokes.get(position);
      binding.searchWord.setText(joke.getSearchWord());
      binding.created.setText(dateFormat.format(joke.getCreated()));
      binding.content.setText(joke.getContent());
      binding.removeFavorite.setOnClickListener((v) -> listener.onRemove(joke));
    }
  }

  @FunctionalInterface
  public interface RemoveJokeListener {

    void onRemove(Joke joke);

  }
}
