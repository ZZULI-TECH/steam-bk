package org.steam.core.service;

import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.User;

/**
 * 用户服务接口
 *
 * @author mingshan
 */
public interface UserService {
    /**
     * 通过id查找用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    User findById(long id);

    /**
     * 通过用户邮箱查找用户
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User findByEmail(String email);

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     * @param version 版本号
     */
    void delete(long id, long version) throws ServiceException;

    /**
     * 添加用户
     *
     * @return 用户id
     */
     long insert(User user);
}
