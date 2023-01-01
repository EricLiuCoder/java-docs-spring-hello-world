package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.User;
import com.pcc.carrental.response.UserDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-01T20:04:40+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setAlias( user.getAlias() );
        userDTO.setMobilePhoneNum( user.getMobilePhoneNum() );
        userDTO.setEmailAddress( user.getEmailAddress() );

        return userDTO;
    }
}
