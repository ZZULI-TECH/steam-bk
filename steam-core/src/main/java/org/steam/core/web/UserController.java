package org.steam.core.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.User;
import org.steam.core.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mingshan
 */
@Api(value = "user")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="通过id获取用户信息", httpMethod="GET")
    @GetMapping("/{id}")
    public ResultModel<User> getUserInfo(@PathVariable Integer id){
        return ResultModel.ok(userService.getById(id));
    }

    @ApiOperation(value="通过用户名获取用户信息", httpMethod="GET")
    @GetMapping("/getByName")
    public ResultModel<User> getUserInfoByName(@RequestParam("name") String name) {
        User user = userService.getOne(new QueryWrapper<User>().eq("name",name));
        return ResultModel.ok(user);
    }

    @ApiOperation(value="更新用户信息", httpMethod="PUT")
    @PutMapping
    public ResultModel<User> updateById(@RequestBody @Valid User user){
        userService.updateById(user);
        return ResultModel.ok();
    }

    @ApiOperation(value="删除", httpMethod="DELETE")
    @DeleteMapping("/{id}")
    public ResultModel<User> delete(@PathVariable Integer id, @RequestParam("version") Long version){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("version", version);
        userService.removeByMap(map);
        return ResultModel.ok();
    }

}
