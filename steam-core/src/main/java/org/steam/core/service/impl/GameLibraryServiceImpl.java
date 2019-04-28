package org.steam.core.service.impl;

import org.steam.core.model.entity.GameLibrary;
import org.steam.core.repository.GameLibraryMapper;
import org.steam.core.service.IGameLibraryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
