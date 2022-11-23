package com.boxportation.services;

import com.boxportation.dto.BoxDto;
import com.boxportation.dto.ItemsDto;

import java.util.List;

public interface BoxService {

    void registerBox(BoxDto boxDto);

    List<ItemsDto> loadBox(String ref);

    List<ItemsDto> ListItemsInBox(String  ref);

    List<BoxDto> availableBoxesForLoading();

    Double BatteryLevel(String ref);


}
