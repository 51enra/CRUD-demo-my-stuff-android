package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder> {

    private final List<Item> items;
    private NavController navController;

     public ItemListRecyclerViewAdapter(NavController navController) {

         this.items = new ArrayList<>();
         this.navController = navController;
    }

    public void updateItems(List<Item> list) {
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        MyStuffItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.my_stuff_item, parent, false);
        return new ViewHolder(listItemBinding, false);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = this.items.get(position);
        holder.binding.setItem(item);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item); //.putInt("itemPos", position);
                navController.navigate(R.id.action_itemListFragment_to_itemDetailsFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final MyStuffItemBinding binding;

        ViewHolder(MyStuffItemBinding binding, Boolean received) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
