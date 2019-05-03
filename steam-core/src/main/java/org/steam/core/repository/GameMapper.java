package org.steam.core.repository;

import org.mapstruct.Mapper;
import org.steam.core.model.entity.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Mapper
public interface GameMapper extends BaseMapper<Game> {

    Game select(Long id);
}
