package com.alan.user.controller;

import com.alan.user.model.UserDemoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Demo2Controller {

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.generateUserDemoModelList());
    }

    private List<UserDemoModel> generateUserDemoModelList(){

        List<UserDemoModel> userDemoModelList = new ArrayList<>();
        userDemoModelList.add(new UserDemoModel(0L, "User01","1234"));
        userDemoModelList.add(new UserDemoModel(1L, "User02","1234"));
        userDemoModelList.add(new UserDemoModel(2L, "User03","1234"));
        userDemoModelList.add(new UserDemoModel(3L, "User04","1234"));
        return userDemoModelList;
    }
}
