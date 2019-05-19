package org.steam.core.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.exception.VersionException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.GameComment;
import org.steam.core.model.entity.GameImage;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.GameCommentVO;
import org.steam.core.model.vo.GameImageVO;
import org.steam.core.model.vo.GameVO;
import org.steam.core.service.IGameCommentService;
import org.steam.core.service.IGameImageService;
import org.steam.core.service.IGameService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2019-04-28
 */
@Api(value = "game")
@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private IGameService gameService;

    @Autowired
    private IGameCommentService gameCommentService;

    @Autowired
    private IGameImageService gameImageService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @ApiOperation(value="新增游戏", httpMethod="POST", notes="新增游戏")
    @PostMapping("add")
    public ResultModel save(@RequestBody GameVO gameVO) {
        Game game = orikaMapperFacade.map(gameVO, Game.class);
        game.setOnSale(true);
        gameService.save(game);

        return ResultModel.ok();
    }

    @ApiOperation(value="删除游戏", httpMethod="DELETE", notes="删除游戏")
    @DeleteMapping("/{id}")
    public ResultModel delete(@PathVariable("id") Long id) {
        gameService.removeById(id);
        return ResultModel.ok();
    }

    @ApiOperation(value="根据id获取游戏", httpMethod="GET", notes="根据id获取游戏")
    @GetMapping("/{id}")
    public ResultModel<GameVO> get(@PathVariable("id") Long id) {
        Game game = gameService.getById(id);
        List<GameComment> comments = gameCommentService.list(new QueryWrapper<GameComment>().eq("game_id", game.getId()));
        game.setComments(comments);
        List<GameImage> images = gameImageService.list(new QueryWrapper<GameImage>().eq("game_id", game.getId()));
        game.setImages(images);
        GameVO gameVO = orikaMapperFacade.map(game, GameVO.class);
        if (!CollectionUtils.isEmpty(game.getComments())) {
            List<GameCommentVO> commentVOS = orikaMapperFacade.mapAsList(game.getComments(), GameCommentVO.class);
            gameVO.setComments(commentVOS);
        }
        
        if (!CollectionUtils.isEmpty(game.getImages())) {
            List<GameImageVO> imageVOS = orikaMapperFacade.mapAsList(game.getImages(), GameImageVO.class);
            gameVO.setImages(imageVOS);
        }

        return ResultModel.ok(gameVO);
    }

    @ApiOperation(value="分页查询", httpMethod="GET", notes="分页查询")
    @GetMapping("/list")
    public ResultModel<IPage> selectList(Integer pageSize, Integer pageNum, GameVO gameVO) {
        Game game = orikaMapperFacade.map(gameVO, Game.class);
        Page<Game> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        if (game.getOnSale() != null) {
            wrapper.eq("on_sale", game.getOnSale());
        }

        if (!StringUtils.isEmpty(game.getKeywords())) {
            wrapper.like("keywords", game.getKeywords());
        }
        if (!StringUtils.isEmpty(game.getPrice())) {
            wrapper.eq("price", game.getPrice());
        }
        if (!StringUtils.isEmpty(game.getType())) {
            wrapper.like("type", game.getType());
        }

        wrapper.orderByDesc("gmt_modified");

        IPage<Game> games = gameService.page(page, wrapper);
        return ResultModel.ok(games);
    }

    @ApiOperation(value="上架", httpMethod="PUT", notes="上架")
    @PutMapping("/{id}/putaway")
    public ResultModel putaway(@PathVariable("id") Long id, @RequestParam("version") Long version) {
        try {
            gameService.putaway(id, version);
        } catch (ServiceException | VersionException e) {
            throw new ServerException(ResultModel.fail(e.getCode(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResultModel.ok();
    }

    @ApiOperation(value="下架", httpMethod="PUT", notes="下架")
    @PutMapping("/{id}/offShelve")
    public ResultModel offShelve(@PathVariable("id") Long id, @RequestParam("version") Long version) {
        try {
            gameService.offShelve(id, version);
        } catch (ServiceException | VersionException e) {
            throw new ServerException(ResultModel.fail(e.getCode(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResultModel.ok();
    }

    @ApiOperation(value = "搜索", httpMethod = "GET")
    @GetMapping("/search")
    public ResultModel search(Integer pageSize, Integer pageNum, GameVO gameVO) {
        Game game = orikaMapperFacade.map(gameVO, Game.class);
        Page<Game> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        if (game.getOnSale() != null) {
            wrapper.eq("on_sale", game.getOnSale());
        }
        wrapper.apply("(UPPER(name) like {0} or UPPER(keywords) like {1} or UPPER(english_name) like {2})", "%"+game.getKeywords().toUpperCase()+"%", "%"+game.getKeywords().toUpperCase()+"%", "%"+game.getKeywords().toUpperCase()+"%");
        wrapper.orderByDesc("gmt_modified");

        IPage<Game> games = gameService.page(page, wrapper);
        return ResultModel.ok(games);
    }
}
