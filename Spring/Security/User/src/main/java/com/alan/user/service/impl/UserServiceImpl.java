package com.alan.user.service.impl;

import com.alan.user.exception.UserException;
import com.alan.user.model.dto.UserDto;
import com.alan.user.model.entity.Profile;
import com.alan.user.model.entity.Role;
import com.alan.user.model.entity.User;
import com.alan.user.model.mapper.UserMapper;
import com.alan.user.repositories.ProfileRepository;
import com.alan.user.repositories.RoleRepository;
import com.alan.user.repositories.UserRepository;
import com.alan.user.service.RoleService;
import com.alan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void create(UserDto userDto) {
        try{
            User user = new User();
            Profile profile = new Profile();
            Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow();
            userMapper.mapUserDto(user, profile, userDto);
            user.setRole(role);
            User userSaved = userRepository.save(user);
            profile.setUser(userSaved);
            profileRepository.save(profile);
        }catch (Exception e){
            throw new UserException(e.getMessage());
        }
    }
}
