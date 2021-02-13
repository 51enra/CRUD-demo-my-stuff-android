package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import de.telekom.sea.mystuff.frontend.android.util.MyStuffViewModel;

public class ItemViewModel extends MyStuffViewModel {
    
    public LiveData<ApiResponse<List<Item>>> getAll() {
        return getMyStuffContext().getItemRepository().getAll();
    }

    public LiveData<ApiResponse<Item>> getById(Long id) {
        return getMyStuffContext().getItemRepository().getById(id);
    }
}




