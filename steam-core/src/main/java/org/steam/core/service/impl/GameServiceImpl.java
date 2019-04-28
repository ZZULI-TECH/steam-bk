package org.steam.core.service.impl;

import org.steam.core.model.entity.Game;
import org.steam.core.repository.GameMapper;
import org.steam.core.service.IGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}