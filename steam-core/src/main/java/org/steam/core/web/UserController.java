package org.steam.core.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.steam.common.model.ResultModel;
import org.steam.common.util.StringUtil;
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
    public ResultModel<User> getUserInfo(@PathVariable Long id){
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
    public ResultModel<User> delete(@PathVariable Long id, @RequestParam("version") Long version){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("version", version);
        userService.removeByMap(map);
        return ResultModel.ok();
    }

    /**
     *
     * @param pageSize
     * @param pageNum
     * @param user
     * @author lyf
     */
    @ApiOperation(value="查询所有，带分页", httpMethod="GET")
    @GetMapping("/list")
    public ResultModel<User> selectList(Integer pageSize, Integer pageNum, User user){
        Page page = new Page();
        page.setSize(pageSize);
        page.setPages(pageNum);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(user.getUsername())) {
            wrapper.eq("username", user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getSex())) {
            wrapper.eq("sex", user.getSex());
        }
        if (!StringUtils.isEmpty(user.getAge())) {
            wrapper.eq("age", user.getAge());
        }
        userService.page(page, wrapper);
        return ResultModel.ok();
    }

}
