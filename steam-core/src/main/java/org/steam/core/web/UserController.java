package org.steam.core.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.exception.VersionException;
import org.steam.common.model.ResultModel;
import org.steam.common.util.MD5Util;
import org.steam.core.json.JSON;
import org.steam.core.model.entity.User;
import org.steam.core.service.UserService;
import org.steam.core.util.TokenUtil;

import javax.validation.Valid;

/**
 * 
 */
@Api(value = "user")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="通过id获取用户信息", httpMethod="GET")
    @GetMapping("/{id}")
//    @JSON(type = User.class ,include = "name,age")
    public ResultModel<User> getUserInfo(@PathVariable Long id){
        return ResultModel.ok(userService.getById(id));
    }

    @ApiOperation(value="通过用户名获取用户信息", httpMethod="GET")
    @GetMapping("/getByName")
    @JSON(type = User.class ,filter = "name,age")
    public ResultModel<User> getUserInfoByName(@RequestParam("name") String name) {
        User user = userService.getOne(new QueryWrapper<User>().eq("name",name));
        return ResultModel.ok(user);
    }

    @ApiOperation(value="更新用户信息", httpMethod="PUT")
    @PutMapping("/update")
    public ResultModel<User> updateById(@RequestBody User user){
        userService.updateById(user);
        return ResultModel.ok();
    }

    @ApiOperation(value="删除", httpMethod="DELETE")
    @DeleteMapping("/{id}")
    public ResultModel<User> delete(@PathVariable Long id, @RequestParam("version") Long version){
        try {
            userService.delete(id, version);
        } catch (VersionException | ServiceException e) {
            throw new ServerException(ResultModel.fail(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResultModel<IPage> selectList(Integer pageSize, Integer pageNum, User user){
        Page<User> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(user.getName())) {
            wrapper.apply("(UPPER(name) like {0} or UPPER(email) like {1})", "%"+user.getName().toUpperCase()+"%", "%"+user.getName().toUpperCase()+"%");
        }
        if (!StringUtils.isEmpty(user.getSex())) {
            wrapper.eq("sex", user.getSex());
        }
        if (!StringUtils.isEmpty(user.getAge())) {
            wrapper.eq("age", user.getAge());
        }
        IPage<User> users =  userService.page(page, wrapper);
        return ResultModel.ok(users);
    }

    /**
     *
     * @param token
     * @return
     */
    @ApiOperation(value="根据token获取用户信息", httpMethod="GET")
    @GetMapping("/getInfo")
    public ResultModel<User> selectList(@RequestParam String token){
      User user = TokenUtil.getUserFromToken(token);
      user.setPassword("");
      return ResultModel.ok(user);
    }

    @ApiOperation(value="检查旧密码是否正确", httpMethod="GET")
    @GetMapping("/checkPwd")
    public ResultModel checkPwd( String userId, String password, String email ){
        User user = userService.getById(userId);
        String newPassword = MD5Util.md5(password, email);
        boolean res = user.getPassword().equals(newPassword);
        return ResultModel.ok(res);
    }

}
