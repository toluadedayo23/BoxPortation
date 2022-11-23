package com.boxportation.services.Implemetations;

import com.boxportation.dto.ItemsDto;
import com.boxportation.exceptions.ItemException;
import com.boxportation.mapper.ItemMapper;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import com.boxportation.repository.ItemRepository;
import com.boxportation.services.ItemServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemServices {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional
    @Override
    public void registerItem(ItemsDto itemsDto) {
        Item newItem = new Item();
        newItem.setName(itemsDto.getName());
        newItem.setWeight(itemsDto.getWeight());
        newItem.setCode(itemsDto.getCode());
        newItem.setStatus(ItemState.UNLOADED);

        itemRepository.save(newItem);
    }

    @Override
    public List<ItemsDto> listAllItems() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemsDto> dtos = itemList.stream()
                .map(itemMapper::mapItemsToDto)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public ItemsDto findItem(String code) {
        Item item = itemRepository.findByCode(code)
                .orElseThrow(() -> new ItemException("Item with CODE NUMBER: " + code + " not found" ));
        ItemsDto dto = itemMapper.mapItemsToDto(item);
        return dto;
    }
}
