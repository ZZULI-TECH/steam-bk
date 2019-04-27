package org.steam.core.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.User;
import org.steam.core.service.UserService;

/**
 * @author mingshan
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/getUserInfo/{uid}")
    public ResultModel<User> getUserInfo(@PathVariable Integer uid){
        return new ResultModel<>(200,"success",userService.getById(uid));
    }

    @GetMapping("/getUserInfoByName/{name}")
    public ResultModel<User> getUserInfoByName(@PathVariable String name){
        User user = userService.getOne(new QueryWrapper<User>().eq("name",name));
        return new ResultModel<>(200,"success",user);
    }

    @PostMapping("/addUser")
    public ResultModel<User> addUser(User user){
        userService.save(user);
        return new ResultModel<>(200,"success");
    }

    @PostMapping("/updateById")
    public ResultModel<User> updateById(User user){
        userService.updateById(user);
        return new ResultModel<>(200,"success",null);
    }

    @PostMapping("/delete/{uid}")
    public ResultModel<User> delete(@PathVariable Integer uid){
        userService.removeById(uid);
        return new ResultModel<>(200,"success",null);
    }


}
