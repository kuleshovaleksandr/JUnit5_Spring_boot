package com.example.junit5_spring_boot.integration;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = {"classpath:application.properties"}) - если надо подключить файл со свойствами
public class ItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    You can use mock to exclude any service from integration test
//    @MockBean
//    private ItemRepository itemRepository;

    @Test
    public void integrationTest() throws JSONException {
        String response = this.restTemplate.getForObject("/items/", String.class);
        String expected = "[{id:1,name:item1,price:3.55,quantity:3,value:10.65}," +
                "{id:2,name:item2,price:9.9,quantity:6,value:59.4}," +
                "{id:3,name:item3,price:15.5,quantity:2,value:31.0}]";
        JSONAssert.assertEquals(expected, response, true);
    }
}
