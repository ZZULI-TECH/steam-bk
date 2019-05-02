package org.steam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.steam.common.exception.ServiceException;
import org.steam.common.exception.VersionException;
import org.steam.core.model.entity.User;

/**
 * 用户服务接口
 *
 * @author mingshan
 */
public interface UserService extends IService<User> {

    /**
     * 删除用户
     *
     * @param id 用户id
     * @param version 版本号
     */
    void delete(long id, long version) throws VersionException, ServiceException;
}
