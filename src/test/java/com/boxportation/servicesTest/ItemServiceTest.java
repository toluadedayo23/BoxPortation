package com.boxportation.servicesTest;

import com.boxportation.dto.ItemsDto;
import com.boxportation.mapper.ItemMapper;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import com.boxportation.repository.ItemRepository;
import com.boxportation.services.Implemetations.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @Captor
    ArgumentCaptor<Item> itemArgumentCaptor;

    @Mock
    private ItemServiceImpl itemService;

    @BeforeEach
    public void setUp(){
        itemService = new ItemServiceImpl(itemRepository, itemMapper);
    }

    @Test
    @DisplayName("Should register item")
    public void shouldRegisterItem(){

        ItemsDto itemsDto = new ItemsDto("dto1", 90.0, "AD1");

        when(itemRepository.existsByCode(itemsDto.getCode())).thenReturn(false);

        Item item = new Item();
        item.setName(itemsDto.getName());
        item.setWeight(itemsDto.getWeight());
        item.setCode(itemsDto.getCode());
        item.setStatus(ItemState.UNLOADED);

        itemService.registerItem(itemsDto);

        verify(itemRepository, times(1)).save(itemArgumentCaptor.capture());

        assertThat(itemArgumentCaptor.getValue().getName()).isEqualTo(itemsDto.getName());
        assertThat(itemArgumentCaptor.getValue().getWeight()).isEqualTo(itemsDto.getWeight());

    }

    @Test
    @DisplayName("should list all items")
    public void listAllItems(){
        Item item = new Item(156l, "item1", 100.0, "CODE1", ItemState.UNLOADED);

        Item item2 = new Item(157l, "item2", 90.0, "CODE2", ItemState.UNLOADED);

        List<Item> itemList = Arrays.asList(item, item2);

        when(itemRepository.findAll()).thenReturn(itemList);

        List<ItemsDto> dtos = itemService.listAllItems();

        assertThat(dtos.size()).isEqualTo(2);
    }


}
