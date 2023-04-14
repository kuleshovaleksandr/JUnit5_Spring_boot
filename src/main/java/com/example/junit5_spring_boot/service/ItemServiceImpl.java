package com.example.junit5_spring_boot.service;

import com.example.junit5_spring_boot.dto.ItemDto;
import com.example.junit5_spring_boot.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemDto getItem() {
        return new ItemDto(1L,"Laptop", 1500.0, 5);
    }

    @Override
    public ItemDto getItemFromDB(long id) {
        ItemDto itemDto = itemRepository.findById(id)
                .map(itemFromDB -> new ItemDto(itemFromDB.getId(),
                        itemFromDB.getName(),
                        itemFromDB.getPrice(),
                        itemFromDB.getQuantity()))
                .orElseThrow();
        return itemDto;
    }

    @Override
    public List<ItemDto> getAllItemsFromDB() {
        List<ItemDto> items = itemRepository.findAll().stream()
                .map(itemFromDB -> new ItemDto(itemFromDB.getId(),
                        itemFromDB.getName(),
                        itemFromDB.getPrice(),
                        itemFromDB.getQuantity()))
                .collect(Collectors.toList());
        return items;
    }
}
