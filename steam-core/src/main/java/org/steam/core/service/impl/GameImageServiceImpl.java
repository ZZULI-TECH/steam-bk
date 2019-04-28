package org.steam.core.service.impl;

import org.steam.core.model.entity.GameImage;
import org.steam.core.repository.GameImageMapper;
import org.steam.core.service.IGameImageService;
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
public class GameImageServiceImpl extends ServiceImpl<GameImageMapper, GameImage> implements IGameImageService {

}
