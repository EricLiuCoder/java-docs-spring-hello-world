package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.User;
import com.pcc.carrental.response.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);
}
