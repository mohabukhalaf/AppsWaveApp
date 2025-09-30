package com.example.appswave.demo.domain.transformers;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.domain.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-30T21:11:56+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class IUserConvertorImpl implements IUserConvertor {

    @Override
    public UserTO toTO(User e) {
        if ( e == null ) {
            return null;
        }

        UserTO userTO = new UserTO();

        userTO.setDateOfBirth( e.getDateOfBirth() );
        userTO.setEmail( e.getEmail() );
        userTO.setFullName( e.getFullName() );
        userTO.setId( e.getId() );
        userTO.setPassword( e.getPassword() );
        userTO.setRole( e.getRole() );

        return userTO;
    }

    @Override
    public List<UserTO> toTOs(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserTO> list1 = new ArrayList<UserTO>( list.size() );
        for ( User user : list ) {
            list1.add( toTO( user ) );
        }

        return list1;
    }

    @Override
    public User toEntity(UserTO t) {
        if ( t == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.dateOfBirth( t.getDateOfBirth() );
        user.email( t.getEmail() );
        user.fullName( t.getFullName() );
        user.id( t.getId() );
        user.password( t.getPassword() );
        user.role( t.getRole() );

        return user.build();
    }

    @Override
    public List<User> toEntities(List<UserTO> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserTO userTO : list ) {
            list1.add( toEntity( userTO ) );
        }

        return list1;
    }
}
