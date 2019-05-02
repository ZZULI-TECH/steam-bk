package org.steam.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steam.common.exception.ServiceException;
import org.steam.common.exception.VersionException;
import org.steam.core.model.entity.User;
import org.steam.core.repository.UserMapper;
import org.steam.core.service.UserService;
import org.steam.core.util.VersionUtil;

/**
 * @author mingshan
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Override
    public void delete(long id, long version) throws ServiceException, VersionException {
        User user = this.baseMapper.selectById(id);
        if (user == null) {
            throw new ServiceException(1005L, "用户不存在");
        }

        VersionUtil.checkVersion(version, user);
        user.setDeleted(1);
        this.baseMapper.updateById(user);
    }
}
