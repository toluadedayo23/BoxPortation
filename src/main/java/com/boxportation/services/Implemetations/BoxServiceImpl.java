package com.boxportation.services.Implemetations;

import com.boxportation.dto.BoxDto;
import com.boxportation.dto.ItemsDto;
import com.boxportation.dto.loadBoxDto;
import com.boxportation.exceptions.BoxException;
import com.boxportation.exceptions.ItemException;
import com.boxportation.model.Box;
import com.boxportation.model.BoxState;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import com.boxportation.repository.BoxRepository;
import com.boxportation.repository.ItemRepository;
import com.boxportation.services.BoxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;
    private final ItemRepository itemRepository;

    @Override
    public void registerBox(BoxDto boxDto) {

        if(boxRepository.existsByTxref(boxDto.getTxref())){
            throw new BoxException("Box with txref already exists, please choose a new txref");
        }
        Box box = new Box();
        box.setTxref(boxDto.getTxref());
        box.setWeightLimit(boxDto.getWeightLimit());
        box.setBatteryCapacity(boxDto.getBatteryCapacity());
        box.setState(BoxState.IDLE);

        boxRepository.save(box);
    }

    @Override
    public List<ItemsDto> loadBox(loadBoxDto loadBoxDto) {
        Box box = boxRepository.findByTxref(loadBoxDto.getTxref())
                .orElseThrow(() -> new BoxException("Box", loadBoxDto.getTxref()));

        Item item = itemRepository.findById(loadBoxDto.getItemId())
                .orElseThrow(() -> new ItemException("Item", loadBoxDto.getItemId()));

        if(item.getStatus() == ItemState.LOADED){
            throw new ItemException("Item with the ITEM ID: " + loadBoxDto.getItemId() + " already loaded");
        }

        if(box.getState() == BoxState.IDLE || box.getState() == BoxState.LOADING ){
            
        }
    }

    @Override
    public List<ItemsDto> ListItemsInBox(String ref) {
        return null;
    }

    @Override
    public List<BoxDto> availableBoxesForLoading() {
        return null;
    }

    @Override
    public Double BatteryLevel(String ref) {
        return null;
    }
}
