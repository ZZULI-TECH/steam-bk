package org.steam.core.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.steam.core.model.entity.GameLibrary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.steam.core.model.entity.Order;
import org.steam.core.model.vo.GameLibraryVO;

/**
 * <p>
 * 用户游戏库 Mapper 接口
 * </p>
 *
 * @since 2019-04-28
 */
public interface GameLibraryMapper extends BaseMapper<GameLibrary> {

    Page<GameLibraryVO> selectGameLibraryPage(Page<GameLibraryVO> page, @Param("uid")Long uid);

    Order getOrder(@Param("uid") Long uid, @Param("gameId") Long gameId);
}
