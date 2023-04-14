package com.example.junit5_spring_boot.controller;

import com.example.junit5_spring_boot.dto.ItemDto;
import com.example.junit5_spring_boot.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/hardcoded")
    public ItemDto getItem() {
        return new ItemDto(1L, "Laptop", 1500.0, 5);
    }

    @GetMapping("/{id}")
    public ItemDto getItemFromService(@PathVariable("id") long id) {
        return itemService.getItemFromDB(id);
    }

    @GetMapping("/")
    public List<ItemDto> getAllItemsFromDB() {
        return itemService.getAllItemsFromDB();
    }
}
