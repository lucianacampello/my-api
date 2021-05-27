package com.myapi.model.auth.mapper;

import com.myapi.model.auth.dto.LoggedDTO;
import com.myapi.model.jwt.UserDetailsImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AuthMapper {
    public static final AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    @Mapping(source = "username", target = "name")
    public abstract LoggedDTO toLoggedDTO(UserDetailsImpl user);
}
