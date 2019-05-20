package org.steam.core.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.exception.ServerException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.GameLibrary;
import org.steam.core.model.entity.User;
import org.steam.core.service.IGameLibraryService;
import org.steam.core.util.TokenUtil;

/**
 * <p>
 * 用户游戏库 前端控制器
 * </p>
 *
 * @since 2019-04-28
 */
@Api(value = "game-library")
@RestController
@RequestMapping("/api/game-library")
public class GameLibraryController {
    @Autowired
    private IGameLibraryService gameLibraryService;


    @ApiOperation(value="用户游戏库列表", httpMethod="GET")
    @Authorization
    @GetMapping("/list/{userId}/{pageNum}/{pageSize}")
    public ResponseEntity<ResultModel> orderList(@PathVariable long userId,
                                                 @PathVariable Integer pageNum ,
                                                 @PathVariable Integer pageSize){
        ResultModel<Page> model;
        model = ResultModel.ok(gameLibraryService.listLibrary(pageNum,pageSize,userId));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @ApiOperation(value="查看用户是否拥有该游戏", httpMethod="GET")
    @Authorization
    @GetMapping("/check/{gameId}/{userId}")
    public ResultModel<GameLibrary> orderList(@PathVariable Long gameId, @PathVariable Long userId){
        QueryWrapper<GameLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("game_id", gameId);
        queryWrapper.eq("uid", userId);
        return ResultModel.ok(gameLibraryService.getOne(queryWrapper));
    }

    @ApiOperation(value="删除用户游戏库", httpMethod="DELETE", notes="删除用户游戏库")
    @DeleteMapping("/{id}")
    public ResultModel delete(@PathVariable Long id) {
        gameLibraryService.removeById(id);
        return ResultModel.ok();
    }

    @ApiOperation(value = "根据用户id和游戏id获取订单号", httpMethod = "GET")
    @GetMapping("getOreder")
    public ResultModel getOreder( Long uid, Long gameId) {
        return ResultModel.ok(gameLibraryService.getOreder(uid, gameId));
    }
}
