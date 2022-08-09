package com.module.usermodule.user.mapper;


import com.module.usermodule.user.dto.UserDto;
import com.module.usermodule.user.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users userPostToUsers(UserDto.UserPostDto userPostDto);
    UserDto.UserResponseDto uesrsToUserResponseDto(Users users);


}
