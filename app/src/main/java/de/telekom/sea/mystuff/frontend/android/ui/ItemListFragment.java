package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.telekom.sea.mystuff.frontend.android.MyStuffApplication;
import de.telekom.sea.mystuff.frontend.android.R;

public class ItemListFragment extends Fragment {

    private ItemViewModel viewModel;
    private ItemListRecyclerViewAdapter listAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.initWithApplication(requireActivity().getApplication());

        // If the NavController is retrieved in onCreateView(), the view doesn't exist yet so we have to pass the Activity.
        // Better retrieve it in onViewCreated!
        //
//        NavController navController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment);
//        listAdapter = new ItemListRecyclerViewAdapter(navController);
//        RecyclerView itemsList = view.findViewById(R.id.rv_items);
//        itemsList.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        itemsList.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        listAdapter = new ItemListRecyclerViewAdapter(navController);
        RecyclerView itemsList = view.findViewById(R.id.rv_items);
        itemsList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        itemsList.setAdapter(listAdapter);

        observeItemsListViewModel();
    }

    private void observeItemsListViewModel() {
        viewModel.getAll().observe(getViewLifecycleOwner(), apiResponse -> {
            if (apiResponse.isSuccessful()) {
                listAdapter.updateItems(apiResponse.body);
            } else {
                ((MyStuffApplication) requireActivity().getApplication()).getMyStuffContext().sendInfoMessage(apiResponse.errorMessage);
            }
        });
    }

}
