package de.telekom.sea.mystuff.frontend.android;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.ui.ItemListViewModel;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsList;
    //    private SwipeRefreshLayout refreshLayout;
    private ItemListViewModel viewModel;
    private ItemListRecyclerViewAdapter listAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsList = findViewById(R.id.rv_items);
        //        refreshLayout = findViewById(R.id.refreshRideListLayout);
        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.initWithApplication(getApplication());
        listAdapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        itemsList.setLayoutManager(new LinearLayoutManager(this));
        itemsList.setAdapter(listAdapter);
        observeItemsListViewModel();
    }

    private void observeItemsListViewModel() {
        viewModel.getItems().observe(this, apiResponse -> {
            if (apiResponse.isSuccessful()) {
                listAdapter.updateItems(apiResponse.body);
            } else {
                ((MyStuffApplication) getApplication()).getMyStuffContext().sendInfoMessage(apiResponse.errorMessage);
            }
        });
    }

}
