package com.myapi.model.user.mapper;

import com.myapi.model.user.dto.UserPostDTO;
import com.myapi.model.user.dto.UserPutDTO;
import com.myapi.model.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostDTO userPostDTO);

    public abstract User toUser(UserPutDTO userPutDTO);
}
