package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface MenuDao {

    MenuDO get(Long menuId);

    List<MenuDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MenuDO menu);

    int update(MenuDO menu);

    int remove(Long menu_id);

    int batchRemove(Long[] menuIds);

    List<MenuDO> listMenuByUserId(Long id);

    List<String> listUserPerms(Long id);

    int updateMenuId(MenuDO menu);

    long getMaxId();
}
