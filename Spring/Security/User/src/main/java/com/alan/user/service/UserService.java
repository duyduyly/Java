package com.alan.user.service;

import com.alan.user.model.dto.UserDto;
import com.alan.user.model.entity.Role;
import com.alan.user.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void create(UserDto userDto);
}
