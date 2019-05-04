package org.steam.core.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.GameImage;
import org.steam.core.model.vo.GameImageVO;
import org.steam.core.model.vo.GameVO;
import org.steam.core.service.IGameImageService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Api(value = "game-image")
@RestController
@RequestMapping("/game-image")
public class GameImageController {

    @Autowired
    private IGameImageService gameImageService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @ApiOperation(value="批量新增游戏图片", httpMethod="POST", notes="批量新增游戏图片")
    @PostMapping
    public ResultModel save(@RequestBody List<GameImageVO> images) {
        List<GameImage> gameImages = orikaMapperFacade.mapAsList(images, GameImage.class);
        gameImageService.saveBatch(gameImages);
        return ResultModel.ok();
    }

    @ApiOperation(value="新增游戏图片", httpMethod="POST", notes="新增游戏图片")
    @PostMapping
    public ResultModel save(@RequestBody GameImageVO gameImageVO) {
        GameImage gameImage = orikaMapperFacade.map(gameImageVO, GameImage.class);
        gameImageService.save(gameImage);
        return ResultModel.ok();
    }

    @ApiOperation(value="删除游戏图片", httpMethod="DELETE", notes="删除游戏图片")
    @DeleteMapping("/{id}")
    public ResultModel delete(@PathVariable Long id) {
        gameImageService.removeById(id);

        return ResultModel.ok();
    }
}
