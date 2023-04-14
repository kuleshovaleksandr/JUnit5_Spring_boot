package com.example.junit5_spring_boot.service;

import com.example.junit5_spring_boot.dto.ItemDto;
import com.example.junit5_spring_boot.entity.Item;

import java.util.List;

public interface ItemService {

    ItemDto getItem();

    ItemDto getItemFromDB(long id);

    List<ItemDto> getAllItemsFromDB();
}
