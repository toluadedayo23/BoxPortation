package com.boxportation.mapper;

import com.boxportation.dto.BoxResponses;

import com.boxportation.model.Box;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BoxMapper {


    BoxResponses mapBoxToResponse(Box box);

}
