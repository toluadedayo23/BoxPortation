package com.boxportation.controllers;

import com.boxportation.dto.ItemsDto;
import com.boxportation.services.ItemServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
@Tag(name = "Items Controller", description = "This REST controller provide services to manage Items in the Boxportation application")
public class ItemController {

    private final ItemServices itemServices;

    @PostMapping
    public ResponseEntity<String> registerItem(@RequestBody @Valid ItemsDto itemsDto) {
        itemServices.registerItem(itemsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Your Item has been registered");
    }

    @GetMapping
    public ResponseEntity<List<ItemsDto>> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemServices.listAllItems());
    }

    @GetMapping("/getItemByCode/{code}")
    public ResponseEntity<ItemsDto> findByCode(@PathVariable("code") String code) {
        return ResponseEntity.status(HttpStatus.OK).body(itemServices.findItem(code));
    }
}
