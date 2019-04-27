package org.steam.core.service.impl;

import org.springframework.stereotype.Service;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.User;
import org.steam.core.service.UserService;

/**
 * @author mingshan
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void delete(long id, long version) throws ServiceException {

    }

    @Override
    public long insert(User user) {
        return 0;
    }
}
