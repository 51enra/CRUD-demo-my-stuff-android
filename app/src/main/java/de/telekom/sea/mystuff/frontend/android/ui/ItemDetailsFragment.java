package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import de.telekom.sea.mystuff.frontend.android.MyStuffApplication;
import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.FragmentItemDetailsBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
//import de.telekom.sea.mystuff.frontend.android.databinding.FragmentItemDetailsBinding;

public class ItemDetailsFragment extends Fragment {

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentItemDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_details, container,false);

        binding.setItem((Item) getArguments().getSerializable("item"));

        return binding.getRoot();

    }

}
