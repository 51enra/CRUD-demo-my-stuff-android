package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import de.telekom.sea.mystuff.frontend.android.MyStuffApplication;
import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.FragmentItemDetailsBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemDetailsFragment extends Fragment {


    private ItemViewModel viewModel;
    private FragmentItemDetailsBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.initWithApplication(requireActivity().getApplication());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_details, container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Option 1: Pass the complete Item in the bundle from ItemListRecyclerViewAdapter (see also there).
        // The Class Item must implement Serializable to be passed as argument!
        // The argument parameter in the nav_graph must be declared accordingly (see also there)!
        //
        // binding.setItem((Item) getArguments().getSerializable("item"));

        // Option 2: Pass only the Item id in the bundle and retrieve the Item directly from the DB
        //
        observeItemViewModel(getArguments().getLong("itemId"));
    }

    // Option 2: Retrieve an Item from the DB
    private void observeItemViewModel(Long id) {
        viewModel.getById(id).observe(getViewLifecycleOwner(), apiResponse -> {
            if (apiResponse.isSuccessful()) {
                binding.setItem(apiResponse.body);
            } else {
                ((MyStuffApplication) requireActivity().getApplication()).getMyStuffContext().sendInfoMessage(apiResponse.errorMessage);
            }
        });
    }

}
