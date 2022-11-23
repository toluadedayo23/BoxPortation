package com.boxportation.services.Implemetations;

import com.boxportation.dto.*;
import com.boxportation.exceptions.BoxException;
import com.boxportation.exceptions.ItemException;
import com.boxportation.mapper.BoxMapper;
import com.boxportation.mapper.ItemMapper;
import com.boxportation.model.Box;
import com.boxportation.model.BoxState;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import com.boxportation.repository.BoxRepository;
import com.boxportation.repository.ItemRepository;
import com.boxportation.services.BoxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final BoxMapper boxMapper;

    @Transactional
    @Override
    public void registerBox(BoxDto boxDto) {

        if(boxRepository.existsByTxref(boxDto.getTxref())){
            throw new BoxException("Box with txref already exists, please choose a new txref");
        }
        Box box = new Box();
        box.setTxref(boxDto.getTxref());
        box.setWeightCarried(0.00);
        box.setBatteryCapacity(boxDto.getBatteryCapacity());
        box.setState(BoxState.IDLE);

        boxRepository.save(box);
    }

    @Transactional
    @Override
    public LoadedItemsResponse loadBox(LoadBoxDto loadBoxDto) {
        Box box = boxRepository.findByTxref(loadBoxDto.getTxref())
                .orElseThrow(() -> new BoxException("Box", loadBoxDto.getTxref()));

        if(box.getBatteryCapacity() < 25.0){
            box.setState(BoxState.IDLE);
            throw new BoxException("Box cannot be loaded, please recharge battery");
        }

        box.setState(BoxState.LOADING);

        Item item = itemRepository.findById(loadBoxDto.getItemId())
                .orElseThrow(() -> new ItemException("Item", loadBoxDto.getItemId()));

        if(item.getStatus() == ItemState.LOADED){
            throw new ItemException("Item with the ITEM ID: " + loadBoxDto.getItemId() + " already loaded");
        }


        if(item.getWeight() > box.getWeightLimit()){
            throw new BoxException("Item: " + item.getName() + " is over the weight limit for Box with the TXREF: " + box.getTxref());
        }

        if(item.getWeight() > box.getWeightLimit()- box.getWeightCarried()){
            throw new BoxException("Item: " + item.getName() + " weight cannot fit, please find another box");
        }


        box.getItemList().add(item);
        box.setState(BoxState.LOADED);
        box.setWeightCarried(box.getWeightCarried() + item.getWeight());
        Box savedBox = boxRepository.save(box);
        item.setStatus(ItemState.LOADED);


        return itemMapper.mapItemsToResponse(savedBox, item);

    }


    @Override
    public List<LoadedItemsResponse> ListItemsInBox(String ref) {
        Box box = boxRepository.findByTxref(ref)
                .orElseThrow(() -> new BoxException("Box", ref));

        List<LoadedItemsResponse> LoadedItemsResponseList = box.getItemList().stream()
                .map(item -> itemMapper.mapItemsToResponse(box, item))
                .collect(Collectors.toList());

        return LoadedItemsResponseList;
    }

    @Override
    public List<BoxResponses> availableBoxesForLoading() {
        List<Box> boxes = boxRepository.getBoxesByStateContainsAndBatteryCapacityGreaterThanEqual(
                BoxState.IDLE.toString(), 25.0);

        List<BoxResponses> boxResponsesList = boxes.stream()
                .map(boxMapper::mapBoxToResponse)
                .collect(Collectors.toList());
        return boxResponsesList;

    }

    @Override
    public Double BatteryLevel(String ref) {
        Box box = boxRepository.findByTxref(ref)
                .orElseThrow(() -> new BoxException("Box", ref));

        return box.getBatteryCapacity();
    }
}
