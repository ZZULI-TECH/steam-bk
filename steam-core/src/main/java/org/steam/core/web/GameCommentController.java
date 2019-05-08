package org.steam.core.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.GameComment;
import org.steam.core.model.vo.GameCommentVO;
import org.steam.core.model.vo.GameVO;
import org.steam.core.service.IGameCommentService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author biao
 * @since 2019-05-03
 */
@Api(value = "game-comment")
@RestController
@RequestMapping("/api/game-comment")
public class GameCommentController {

    @Autowired
    private IGameCommentService gameCommentService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @ApiOperation(value="新增游戏评论", httpMethod="POST", notes="新增游戏评论")
    @PostMapping
    public ResultModel save(@RequestBody GameCommentVO gameCommentVO) {
        gameCommentVO.setDeleted(0);
        GameComment gameComment = orikaMapperFacade.map(gameCommentVO, GameComment.class);
        gameCommentService.save(gameComment);
        return ResultModel.ok();
    }
}
