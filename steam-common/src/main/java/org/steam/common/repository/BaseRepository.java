package org.steam.common.repository;

import java.util.List;

/**
 * 基础仓储
 *
 * @author mingshan
 */
public interface BaseRepository<T> {
    /**
     * 获取全部信息
     *
     * @return 实体列表
     */
    List<T> getAll();

    /**
     * 通过id查询
     * @param id
     * @return 实体
     */
    T get(long id);

    /**
     * 插入
     *
     * @param entity 实体
     */
    void insert(T entity);

    /**
     * 更新
     *
     * @param entity 实体
     */
    void update(T entity);

    /**
     * 删除
     *
     * @param id 实体id
     * @param version 乐观锁版本号
     */
    void delete(long id, long version);
}
