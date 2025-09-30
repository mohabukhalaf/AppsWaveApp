package com.example.appswave.demo.domain.transformers;

import com.example.appswave.demo.domain.dtos.NewsTO;
import com.example.appswave.demo.domain.entities.News;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-30T21:40:19+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class INewsConvertorImpl implements INewsConvertor {

    @Override
    public NewsTO toTO(News e) {
        if ( e == null ) {
            return null;
        }

        NewsTO newsTO = new NewsTO();

        newsTO.setDeleteAction( e.isDeleteAction() );
        newsTO.setDeletedAt( e.getDeletedAt() );
        newsTO.setDescription( e.getDescription() );
        newsTO.setDescriptionAr( e.getDescriptionAr() );
        newsTO.setId( e.getId() );
        newsTO.setImageUrl( e.getImageUrl() );
        newsTO.setPublishDate( e.getPublishDate() );
        newsTO.setStatus( e.getStatus() );
        newsTO.setTitle( e.getTitle() );
        newsTO.setTitleAr( e.getTitleAr() );

        return newsTO;
    }

    @Override
    public List<NewsTO> toTOs(List<News> list) {
        if ( list == null ) {
            return null;
        }

        List<NewsTO> list1 = new ArrayList<NewsTO>( list.size() );
        for ( News news : list ) {
            list1.add( toTO( news ) );
        }

        return list1;
    }

    @Override
    public News toEntity(NewsTO t) {
        if ( t == null ) {
            return null;
        }

        News.NewsBuilder news = News.builder();

        news.deleteAction( t.isDeleteAction() );
        news.deletedAt( t.getDeletedAt() );
        news.description( t.getDescription() );
        news.descriptionAr( t.getDescriptionAr() );
        news.id( t.getId() );
        news.imageUrl( t.getImageUrl() );
        news.publishDate( t.getPublishDate() );
        news.status( t.getStatus() );
        news.title( t.getTitle() );
        news.titleAr( t.getTitleAr() );

        return news.build();
    }

    @Override
    public List<News> toEntities(List<NewsTO> list) {
        if ( list == null ) {
            return null;
        }

        List<News> list1 = new ArrayList<News>( list.size() );
        for ( NewsTO newsTO : list ) {
            list1.add( toEntity( newsTO ) );
        }

        return list1;
    }
}
