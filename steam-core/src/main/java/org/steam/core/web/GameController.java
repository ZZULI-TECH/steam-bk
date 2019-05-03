package org.steam.core.web;


import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Game;
import org.steam.core.model.vo.GameVO;
import org.steam.core.service.IGameService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Api(value = "game")
@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private IGameService gameService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    public ResultModel save(@RequestBody GameVO gameVO) {
        Game game = orikaMapperFacade.map(gameVO, Game.class);
        gameService.save(game);

        return ResultModel.ok();
    }

    public ResultModel delete(@PathVariable("id") Long id) {
        return ResultModel.ok();
    }
}
