package com.example.appswave.demo.domain.transformers;

import org.mapstruct.Mapper;

import com.example.appswave.demo.domain.dtos.NewsTO;
import com.example.appswave.demo.domain.entities.News;

@Mapper(componentModel = "spring")
public interface INewsConvertor extends IGenericConverter<NewsTO, News> {

}
