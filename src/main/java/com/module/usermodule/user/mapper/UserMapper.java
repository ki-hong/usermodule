package com.module.usermodule.user.mapper;


import com.module.usermodule.user.dto.UserDto;
import com.module.usermodule.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostToUser(UserDto.UserPostDto userPostDto);


}
