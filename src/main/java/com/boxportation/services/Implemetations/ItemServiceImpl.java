package com.boxportation.services.Implemetations;

import com.boxportation.dto.ItemsDto;
import com.boxportation.exceptions.BoxException;
import com.boxportation.exceptions.ItemException;
import com.boxportation.mapper.ItemMapper;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import com.boxportation.repository.ItemRepository;
import com.boxportation.services.ItemServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        if(itemRepository.existsByCode(itemsDto.getCode())){
            throw new ItemException("Item with Code name: " + itemsDto.getCode()+ " already exists, please choose a new code");
        }
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
