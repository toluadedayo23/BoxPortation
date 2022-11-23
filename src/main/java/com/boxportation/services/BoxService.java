package com.boxportation.services;

import com.boxportation.dto.*;

import java.util.List;

public interface BoxService {

    void registerBox(BoxDto boxDto);

    LoadedItemsResponse loadBox(LoadBoxDto loadBoxDto);

    List<LoadedItemsResponse> ListItemsInBox(String  ref);

    List<BoxResponses> availableBoxesForLoading();

    Double BatteryLevel(String ref);


}
