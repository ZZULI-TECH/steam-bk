package org.steam.core.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.steam.core.model.entity.GameLibrary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.steam.core.model.vo.GameLibraryVO;

/**
 * <p>
 * 用户游戏库 Mapper 接口
 * </p>
 *
 * @since 2019-04-28
 */
public interface GameLibraryMapper extends BaseMapper<GameLibrary> {

    @Select("SELECT\n" +
            "            gl.id,gl.uid,gl.game_id,gl.gmt_create,gl.gmt_modified,gl.version,\n" +
            "            g.name,g.type,g.english_name,g.click_count,g.sales,g.price,g.cover,g.keywords,g.remark,g.content,g.download_url\n" +
            "        FROM\n" +
            "            t_game_library gl\n" +
            "            LEFT JOIN t_game g on gl.game_id = g.id\n" +
            "        WHERE\n" +
            "            gl.uid = #{uid} AND gl.deleted = 0")
    Page<GameLibraryVO> selectGameLibraryPage(Page<GameLibraryVO> page, @Param("uid")Long uid);

}
