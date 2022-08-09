package com.module.usermodule.user.mapper;


import com.module.usermodule.user.dto.UserDto;
import com.module.usermodule.user.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users userPostToUsers(UserDto.UserPostDto userPostDto);


}
