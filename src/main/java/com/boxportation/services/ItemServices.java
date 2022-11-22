package com.boxportation.services;

import com.boxportation.dto.ItemsDto;

import java.util.List;

public interface ItemServices {

    ItemsDto registerItem(ItemsDto itemsDto);

    List<ItemsDto> listAllItems();


}
