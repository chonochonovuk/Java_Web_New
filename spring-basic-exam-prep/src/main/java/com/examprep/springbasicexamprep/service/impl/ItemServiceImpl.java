package com.examprep.springbasicexamprep.service.impl;

import com.examprep.springbasicexamprep.model.entity.Item;
import com.examprep.springbasicexamprep.model.service.CategoryServiceModel;
import com.examprep.springbasicexamprep.model.service.ItemServiceModel;
import com.examprep.springbasicexamprep.model.view.ItemViewModel;
import com.examprep.springbasicexamprep.repository.ItemRepository;
import com.examprep.springbasicexamprep.service.CategoryService;
import com.examprep.springbasicexamprep.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemServiceModel addItem(ItemServiceModel itemServiceModel) {
        CategoryServiceModel categoryServiceModel = this.categoryService
                .findByCategoryName(itemServiceModel.getCategory().getCategoryName());
        itemServiceModel.setCategory(categoryServiceModel);
        Item item = this.modelMapper.map(itemServiceModel,Item.class);
        this.itemRepository.saveAndFlush(item);
        return this.modelMapper.map(item,ItemServiceModel.class);
    }

    @Override
    public List<ItemViewModel> findAllItems() {

        return this.itemRepository.findAll()
                .stream()
                .map(item -> {
                   ItemViewModel itemViewModel = this.modelMapper.map(item,ItemViewModel.class);
                   itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg",item.getGender(),item.getCategory().getCategoryName().name()));
                   return itemViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findById(String id) {
        return this.itemRepository.findById(id).map(item ->  {
            ItemViewModel itemViewModel = this.modelMapper.map(item,ItemViewModel.class);
            itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg",item.getGender(),item.getCategory().getCategoryName().name()));
            return itemViewModel;})
                .orElse(null);
    }

    @Override
    public void deleteItem(String id) {
        this.itemRepository.deleteById(id);
    }
}
