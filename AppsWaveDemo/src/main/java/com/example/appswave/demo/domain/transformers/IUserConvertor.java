package com.example.appswave.demo.domain.transformers;

import org.mapstruct.Mapper;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.domain.entities.User;

@Mapper(componentModel = "spring")
public interface IUserConvertor extends IGenericConverter<UserTO, User> {

}
