package com.example.junit5_spring_boot.repository;

import com.example.junit5_spring_boot.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED) - транзакции не открываются
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private TestEntityManager entityManager;

    @Test
    public void findAllItems() {
        List<Item> items = itemRepository.findAll();
        Assertions.assertEquals(3,  items.size());
//        entityManager.persist();
    }

    @Test
    public void findItemById() {
        Item item = itemRepository.findById(1L).orElseThrow();
        Assertions.assertEquals(new Item(1L, "item1", 3.55, 3),  item);
    }
}
