package org.steam.core.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.steam.core.model.entity.User;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
