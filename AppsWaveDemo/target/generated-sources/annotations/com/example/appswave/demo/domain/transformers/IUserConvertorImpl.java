package com.example.appswave.demo.domain.transformers;

import com.example.appswave.demo.domain.dtos.UserTO;
import com.example.appswave.demo.domain.entities.User;
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
public class IUserConvertorImpl implements IUserConvertor {

    @Override
    public UserTO toTO(User e) {
        if ( e == null ) {
            return null;
        }

        UserTO userTO = new UserTO();

        userTO.setId( e.getId() );
        userTO.setFullName( e.getFullName() );
        userTO.setEmail( e.getEmail() );
        userTO.setPassword( e.getPassword() );
        userTO.setDateOfBirth( e.getDateOfBirth() );
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

        user.id( t.getId() );
        user.fullName( t.getFullName() );
        user.email( t.getEmail() );
        user.password( t.getPassword() );
        user.dateOfBirth( t.getDateOfBirth() );
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
