package com.examprep.springbasicexamprep.service;

import com.examprep.springbasicexamprep.model.service.ItemServiceModel;
import com.examprep.springbasicexamprep.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    ItemServiceModel addItem(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel findById(String id);

    void deleteItem(String id);
}
