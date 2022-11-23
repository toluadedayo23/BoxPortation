package com.boxportation.services;

import com.boxportation.dto.ItemsDto;
import com.boxportation.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemServices {

    void registerItem(ItemsDto itemsDto);

    List<ItemsDto> listAllItems();

    ItemsDto findItem(String code);


}
