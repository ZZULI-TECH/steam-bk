package org.steam.core.service.impl;

import org.steam.core.model.entity.GameComment;
import org.steam.core.repository.GameCommentMapper;
import org.steam.core.service.IGameCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2019-05-03
 */
@Service
public class GameCommentServiceImpl extends ServiceImpl<GameCommentMapper, GameComment> implements IGameCommentService {

}
