package org.steam.core.repository;

import org.steam.core.model.entity.OrderGame;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2019-05-02
 */
public interface OrderGameMapper extends BaseMapper<OrderGame> {

    List<OrderGame> selectOrderGameList(Long orderId);

}
