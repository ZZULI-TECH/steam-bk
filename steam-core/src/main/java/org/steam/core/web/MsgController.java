package org.steam.core.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.Msg;
import org.steam.core.model.vo.GameVO;
import org.steam.core.model.vo.MsgBkVo;
import org.steam.core.model.vo.MsgVO;
import org.steam.core.service.IMsgService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2019-05-20
 */
@RestController
@RequestMapping("/api/msg")
public class MsgController {
    @Autowired
    private IMsgService msgService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @ApiOperation(value="新增留言", httpMethod="POST", notes="新增留言")
    @PostMapping("/add")
    public ResultModel save(@RequestBody MsgVO msgVO) {
        Msg msg = orikaMapperFacade.map(msgVO, Msg.class);
        msgService.save(msg);
        return ResultModel.ok();
    }

    @ApiOperation(value="回复留言", httpMethod="POST", notes="回复留言")
    @PostMapping("/back")
    public ResultModel back(@RequestBody MsgBkVo msgBkVo) {
        Msg msg =new Msg();
        msg.setId(msgBkVo.getId());
        msg.setMsgBk(msgBkVo.getMsgBk());
        msgService.updateById(msg);
        return ResultModel.ok();
    }

    @ApiOperation(value="删除留言", httpMethod="DELETE", notes="删除留言")
    @DeleteMapping("/{id}")
    public ResultModel delete(@PathVariable("id") Long id) {
        msgService.removeById(id);
        return ResultModel.ok();
    }

    @ApiOperation(value="留言列表", httpMethod="GET", notes="留言列表")
    @GetMapping("/{page}/{size}")
    public ResultModel delete(@PathVariable("page") Integer page,@PathVariable("size") Integer size) {
        if(page==null){
            page=1;
        }
        if(size==null){
            size=30;
        }
        Page<Msg> msgPage = msgService.selectMsgList(page, size);
        return ResultModel.ok(msgPage);
    }


}
