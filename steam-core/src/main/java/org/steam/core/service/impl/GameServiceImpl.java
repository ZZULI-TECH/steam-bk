package org.steam.core.service.impl;

import org.steam.common.exception.ServiceException;
import org.steam.common.exception.VersionException;
import org.steam.core.model.entity.Game;
import org.steam.core.repository.GameMapper;
import org.steam.core.service.IGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.steam.core.util.VersionUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements IGameService {

    @Override
    public void putaway(Long id, Long version) throws ServiceException, VersionException {
        Game game = this.baseMapper.selectById(id);
        if (game == null) {
            throw new ServiceException(1201, "游戏不存在");
        }

        if (game.getOnSale()) {
            return;
        }
        VersionUtil.checkVersion(version, game);
        game.setOnSale(true);
        this.baseMapper.updateById(game);
    }

    @Override
    public void offShelve(Long id, Long version) throws ServiceException, VersionException {
        Game game = this.baseMapper.selectById(id);
        if (game == null) {
            throw new ServiceException(1201, "游戏不存在");
        }

        if (!game.getOnSale()) {
            return;
        }

        VersionUtil.checkVersion(version, game);
        game.setOnSale(false);
        this.baseMapper.updateById(game);
    }
}
