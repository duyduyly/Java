package com.alan.user.model.mapper;

import com.alan.user.model.dto.UserDto;
import com.alan.user.model.entity.Profile;
import com.alan.user.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public void mapUserDto(User user, Profile profile, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        profile.setFirstName(userDto.getFirstName());
        profile.setLastName(userDto.getLastName());
    }
}
