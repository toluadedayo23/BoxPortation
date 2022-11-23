package com.boxportation.mapper;

import com.boxportation.dto.ItemsDto;
import com.boxportation.dto.LoadedItemsResponse;
import com.boxportation.model.Box;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {

    @Mapping(target = "boxId", source = "box.txref")
    @Mapping(target = "name", source = "item.name")
    @Mapping(target = "weight", source = "item.weight")
    @Mapping(target = "status", expression = "java(checkItemStatus(item))")
    public  abstract LoadedItemsResponse mapItemsToResponse(Box box, Item item);


    public abstract ItemsDto mapItemsToDto(Item item);


    String checkItemStatus(Item item){
        if(item.getStatus() == ItemState.LOADED){
            return ItemState.LOADED.toString();
        }
        return ItemState.UNLOADED.toString();
    }


}
