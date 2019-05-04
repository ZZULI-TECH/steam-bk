package org.steam.core.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.GameImage;
import org.steam.core.model.entity.GameLibrary;
import org.steam.core.model.vo.GameImageVO;
import org.steam.core.model.vo.GameLibraryVO;
import org.steam.core.service.IGameLibraryService;
import org.steam.core.service.impl.GameLibraryServiceImpl;

import java.util.List;

/**
 * <p>
 * 用户游戏库 前端控制器
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Api(value = "game-library")
@RestController
@RequestMapping("/api/game-library")
public class GameLibraryController {
    @Autowired
    private IGameLibraryService gameLibraryService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @ApiOperation(value="新增用户游戏库", httpMethod="POST", notes="新增用户游戏库")
    @PostMapping
    public ResultModel save(@RequestBody GameLibraryVO gameLibraryVO) {
        GameLibrary gameLibrary = orikaMapperFacade.map(gameLibraryVO, GameLibrary.class);
        gameLibraryService.save(gameLibrary);
        return ResultModel.ok();
    }

    @ApiOperation(value="删除用户游戏库", httpMethod="DELETE", notes="删除用户游戏库")
    @DeleteMapping("/{id}")
    public ResultModel delete(@PathVariable Long id) {
        gameLibraryService.removeById(id);

        return ResultModel.ok();
    }
}
