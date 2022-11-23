package com.boxportation.controllers;

import com.boxportation.dto.BoxDto;
import com.boxportation.dto.LoadBoxDto;
import com.boxportation.dto.LoadedItemsResponse;
import com.boxportation.services.BoxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/box")
public class BoxController {

    private final BoxService boxService;

    @PostMapping
    public ResponseEntity<String> registerBox(@RequestBody @Valid BoxDto boxDto){
        boxService.registerBox(boxDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Your Box has been created");
    }

    @PostMapping("/loadBox")
    public ResponseEntity<LoadedItemsResponse> loadBox(@RequestBody @Valid LoadBoxDto loadBoxDto){
        return new ResponseEntity<LoadedItemsResponse>(boxService.loadBox(loadBoxDto), HttpStatus.OK);
    }

    @GetMapping("/ListItemsInBox/{txref}")
    public ResponseEntity<List<LoadedItemsResponse>>listItemsInBox(@PathVariable("txref") String txref){
        return new ResponseEntity<List<LoadedItemsResponse>>(boxService.ListItemsInBox(txref), HttpStatus.OK);
    }

    @GetMapping("/getBoxesAvailableForLoading")
    public ResponseEntity<String> getBoxesAvailableForLoading(){
        boxService.availableBoxesForLoading();
        return ResponseEntity.status(HttpStatus.CREATED).body("Your Box has been created");

    }

    @GetMapping("/checkBatteryLevel/{txref}")
    public ResponseEntity<Double> checkBatteryLevel(@PathVariable("txref") String txref){
        return new ResponseEntity<Double>(boxService.BatteryLevel(txref), HttpStatus.OK);
    }


}
