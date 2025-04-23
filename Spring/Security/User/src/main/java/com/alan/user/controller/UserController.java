package com.alan.user.controller;

import com.alan.user.common.response.CommonResponse;
import com.alan.user.model.dto.UserDto;
import com.alan.user.service.RoleService;
import com.alan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResponse create(@RequestBody UserDto userDto) {
        userService.create(userDto);
        return CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("SUCCESS")
                .data(null)
                .build();
    }

    @PostMapping("/update/{userId}")
    public CommonResponse update(@PathVariable("userId") Long userId){

        return CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("SUCCESS")
                .data(null)
                .build();
    }

    @PostMapping("/delete/{userId}")
    public CommonResponse unActive(@PathVariable("userId") Long userId){

        return CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("SUCCESS")
                .data(null)
                .build();
    }

    @GetMapping("/roles")
    public CommonResponse getListRole(){

        return CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("SUCCESS")
                .data(roleService.findAll())
                .build();
    }
}
