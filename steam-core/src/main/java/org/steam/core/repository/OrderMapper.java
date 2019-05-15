package org.steam.core.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.steam.core.model.entity.Order;
import org.steam.core.model.vo.OrderListVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    Order selectOrderByOrderId(Long id);

    List<OrderListVo> selectOrderListByUid(Page page, @Param("order") Order order);

}
