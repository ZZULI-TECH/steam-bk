package org.steam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.steam.common.constant.GameTypeEnum;
import org.steam.core.model.entity.GameLibrary;
import org.steam.core.model.entity.OrderGame;
import org.steam.core.model.vo.GameLibraryVO;
import org.steam.core.repository.GameLibraryMapper;
import org.steam.core.service.IGameLibraryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.steam.core.service.IOrderGameService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户游戏库 服务实现类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Service
public class GameLibraryServiceImpl extends ServiceImpl<GameLibraryMapper, GameLibrary> implements IGameLibraryService {

    @Autowired
    private IOrderGameService orderGameService;

    @Override
    public void addToLibrary(Long orderId, Long uid) {
        List<OrderGame> orderGames = orderGameService.list(new QueryWrapper<OrderGame>().eq("order_id", orderId));
        if(orderGames == null){
            return;
        }
        List<GameLibrary> gameLibraries=new ArrayList<>();
        for (OrderGame orderGame:orderGames){
            if (GameTypeEnum.DIGITAL.getCode().equals(orderGame.getGameType())){
                GameLibrary gameLibrary=new GameLibrary();
                gameLibrary.setGameId(orderGame.getGameId()).setUid(uid);
                gameLibraries.add(gameLibrary);
            }
        }
        if(gameLibraries.size() > 1){
            this.saveBatch(gameLibraries);
        }else if(gameLibraries.size() == 1){
            this.save(gameLibraries.get(0));
        }
    }

    @Override
    public Page<GameLibraryVO> listLibrary(Integer page, Integer size, Long uid) {
        if(page==null){
            page=1;
        }
        if(size==null){
            size=10;
        }
        Page<GameLibraryVO> pages=new Page<>(page,size);
        return this.baseMapper.selectGameLibraryPage(pages,uid);
    }
}
