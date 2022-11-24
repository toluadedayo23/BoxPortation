package com.boxportation.servicesTest;

import com.boxportation.dto.BoxDto;
import com.boxportation.dto.BoxResponses;
import com.boxportation.dto.LoadBoxDto;
import com.boxportation.dto.LoadedItemsResponse;
import com.boxportation.exceptions.BoxException;
import com.boxportation.mapper.BoxMapper;
import com.boxportation.mapper.ItemMapper;
import com.boxportation.model.Box;
import com.boxportation.model.BoxState;
import com.boxportation.model.Item;
import com.boxportation.model.ItemState;
import com.boxportation.repository.BoxRepository;
import com.boxportation.repository.ItemRepository;
import com.boxportation.services.Implemetations.BoxServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoxServiceTest {

    @Mock
    private BoxRepository boxRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private BoxMapper boxMapper;

    @Mock
    private BoxServiceImpl boxService;

    @Captor
    ArgumentCaptor<Box> boxArgumentCaptor;

    @BeforeEach
    public void setUp() {
        boxService = new BoxServiceImpl(boxRepository, itemRepository, itemMapper, boxMapper);
    }

    @Test
    @DisplayName("should register Box")
    public void shouldRegisterBox() throws BoxException {
        BoxDto boxDto = new BoxDto("boxTest1", 87.0);
        Box box = new Box();


        when(boxRepository.existsByTxref(boxDto.getTxref())).thenReturn(false);
        box.setTxref(boxDto.getTxref());
        box.setWeightCarried(0.00);
        box.setBatteryCapacity(boxDto.getBatteryCapacity());
        box.setState(BoxState.IDLE);

        boxService.registerBox(boxDto);

        //verify(boxRepository, times(1)).save(box);
        verify(boxRepository, times(1)).save(boxArgumentCaptor.capture());

        assertThat(boxArgumentCaptor.getValue().getTxref()).isEqualTo(boxDto.getTxref());
        assertThat(boxArgumentCaptor.getValue().getBatteryCapacity()).isEqualTo(boxDto.getBatteryCapacity());

    }

    @Test
    @DisplayName("should list items in specified box")
    public void shouldListItemsInBox() {
//        LoadBoxDto loadBoxDto = new LoadBoxDto("boxTest1", 156l);

        Item item = new Item(156l, "item1", 100.0, "CODE1", ItemState.UNLOADED);

        Item item2 = new Item(157l, "item2", 90.0, "CODE2", ItemState.UNLOADED);

        List<Item> itemList = Arrays.asList(item, item2);

        Box box = new Box(123l, "boxTest1", 200.0, 87.0, BoxState.IDLE, 0.0, itemList);

        when(boxRepository.findByTxref(box.getTxref())).thenReturn(Optional.of(box));

        List<LoadedItemsResponse> loadedItemsResponseList = boxService.ListItemsInBox(box.getTxref());

        assertThat(loadedItemsResponseList.size()).isEqualTo(itemList.size());

    }

    @Test
    @DisplayName("should return battery level")
    public void shouldReturnBatteryLevel() {
        Box box = new Box(123l, "boxTest1", 200.0, 87.0, BoxState.IDLE, 0.0, Collections.emptyList());


        when(boxRepository.findByTxref(box.getTxref())).thenReturn(Optional.of(box));

        Double batteryLevel = boxService.BatteryLevel(box.getTxref());

        assertThat(batteryLevel).isEqualTo(87.0);
    }

}
