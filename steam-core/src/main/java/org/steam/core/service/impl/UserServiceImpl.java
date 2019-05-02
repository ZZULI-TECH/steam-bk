package org.steam.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.steam.core.model.entity.User;
import org.steam.core.repository.UserMapper;
import org.steam.core.service.UserService;

/**
 * @author mingshan
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
