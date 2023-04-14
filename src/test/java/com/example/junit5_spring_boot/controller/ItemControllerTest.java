package com.example.junit5_spring_boot.controller;

import com.example.junit5_spring_boot.dto.ItemDto;
import com.example.junit5_spring_boot.service.ItemService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private final String actualResponse = "{\"id\":1,\"name\":\"Screen\",\"price\":545.0}";

    @Test
    public void itemTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/items/hardcoded")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{id:1,name:Laptop,price:1500.0,quantity=5}"))
                .andReturn();
    }

    @Test
    public void getAllItemsFromDB() throws Exception {
        Mockito.when(itemService.getAllItemsFromDB())
                .thenReturn(List.of(new ItemDto(1L, "item1", 3.55, 3),
                        new ItemDto(2L, "item2", 9.9, 6),
                        new ItemDto(3L, "item3", 15.5, 2)));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/items/")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{id:1,name:item1,price:3.55,quantity:3},{},{}]"))
                .andReturn();
    }

    @Test
    public void getItemFromService() throws Exception {
        Mockito.when(itemService.getItem()).thenReturn(new ItemDto(1L, "Laptop", 1500.0, 5));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/items/1}")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{id:1,name:Laptop,price:1500.0,quantity:5}"))
                .andReturn();
    }

    @Test
    public void parseJson() {
        //Json Path
        String response = "[{\"id\":1,\"name\":\"item1\",\"price\":3.55,\"quantity\":3}," +
                "{\"id\":2,\"name\":\"item2\",\"price\":9.0,\"quantity\":5}," +
                "{\"id\":3,\"name\":\"item3\",\"price\":15.5,\"quantity\":10}]";
        DocumentContext context = JsonPath.parse(response);
        int length = context.read("$.length()");
        Assertions.assertEquals(3, length);

        List<Integer> ids = context.read("$..id");
        assertThat(ids).containsExactly(1, 2, 3);

        System.out.println(ids);
        System.out.println(context.read("$.[1]").toString());
        System.out.println(context.read("$.[0:2]").toString());
        System.out.println(context.read("$.[?(@.name=='item3')]").toString());
    }

    @Test
    public void jsonStrictMatches() throws JSONException {
        String expectedResponse = "{\"id\":1,\"name\":\"Screen\",\"price\":545.0}";
        JSONAssert.assertEquals(expectedResponse, actualResponse , true);
    }

    @Test
    public void jsonNotStrictMatches() throws JSONException {
        String expectedResponse = "{id:1,name:Screen}";
        JSONAssert.assertEquals(expectedResponse, actualResponse , false);
    }
}
