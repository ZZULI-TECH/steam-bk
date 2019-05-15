package org.steam.core.service.impl;

import org.steam.core.model.entity.UserMessage;
import org.steam.core.repository.UserMessageMapper;
import org.steam.core.service.IUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2019-04-28
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements IUserMessageService {

}
