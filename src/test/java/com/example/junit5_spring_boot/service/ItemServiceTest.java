package com.example.junit5_spring_boot.service;

import com.example.junit5_spring_boot.dto.ItemDto;
import com.example.junit5_spring_boot.entity.Item;
import com.example.junit5_spring_boot.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void getRightSizeListOfItemsFromDB() {
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(new Item(1L, "item1", 3.5, 3),
                new Item(2L, "item2", 9.0, 6),
                new Item(3L, "item3", 15.5, 2)));

        List<ItemDto> items = itemService.getAllItemsFromDB();

        Assertions.assertEquals(3, items.size());
    }

    @Test
    public void getItemsValueFromDB() {
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(new Item(1L, "item1", 3.5, 3),
                new Item(2L, "item2", 9.9, 6),
                new Item(3L, "item3", 15.5, 2)));

        List<ItemDto> items = itemService.getAllItemsFromDB();

        Assertions.assertEquals(10.5, items.get(0).getValue());
        Assertions.assertEquals(59.4, items.get(1).getValue());
        Assertions.assertEquals(31, items.get(2).getValue());
    }
}
