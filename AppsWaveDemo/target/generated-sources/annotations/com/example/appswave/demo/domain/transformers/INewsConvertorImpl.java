package com.example.appswave.demo.domain.transformers;

import com.example.appswave.demo.domain.dtos.NewsTO;
import com.example.appswave.demo.domain.entities.News;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T00:23:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class INewsConvertorImpl implements INewsConvertor {

    @Override
    public NewsTO toTO(News e) {
        if ( e == null ) {
            return null;
        }

        NewsTO newsTO = new NewsTO();

        newsTO.setId( e.getId() );
        newsTO.setTitle( e.getTitle() );
        newsTO.setTitleAr( e.getTitleAr() );
        newsTO.setDescription( e.getDescription() );
        newsTO.setDescriptionAr( e.getDescriptionAr() );
        newsTO.setPublishDate( e.getPublishDate() );
        newsTO.setImageUrl( e.getImageUrl() );
        newsTO.setStatus( e.getStatus() );
        newsTO.setDeletedAt( e.getDeletedAt() );
        newsTO.setDeleteAction( e.isDeleteAction() );

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

        news.id( t.getId() );
        news.title( t.getTitle() );
        news.titleAr( t.getTitleAr() );
        news.description( t.getDescription() );
        news.descriptionAr( t.getDescriptionAr() );
        news.publishDate( t.getPublishDate() );
        news.imageUrl( t.getImageUrl() );
        news.status( t.getStatus() );
        news.deleteAction( t.isDeleteAction() );
        news.deletedAt( t.getDeletedAt() );

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
