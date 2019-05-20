package org.steam.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.steam.core.model.entity.Msg;
import org.steam.core.repository.MsgMapper;
import org.steam.core.service.IMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2019-05-20
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements IMsgService {

    @Override
    public Page<Msg> selectMsgList(Integer page, Integer size) {
        Page<Msg> pages=new Page<>(page,size);
        return this.baseMapper.selectMsgList(pages);
    }
}
