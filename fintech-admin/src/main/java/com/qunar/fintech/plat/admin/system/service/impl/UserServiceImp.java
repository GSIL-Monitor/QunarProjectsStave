package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.system.dao.entity.UserDO;
import com.qunar.fintech.plat.admin.system.dao.entity.UserRoleDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.DeptDao;
import com.qunar.fintech.plat.admin.system.dao.mapper.UserDao;
import com.qunar.fintech.plat.admin.system.dao.mapper.UserRoleDao;
import com.qunar.fintech.plat.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Service
public class UserServiceImp implements UserService {

    @Override
    public UserDO get(Long id) {
        List<Long> roleIds = userRoleMapper.listRoleId(id);
        UserDO user = userMapper.get(id);
        if(sysDeptMapper.get(user.getDeptId())!=null){
            user.setDeptName(sysDeptMapper.get(user.getDeptId()).getName());
        }
        user.setroleIds(roleIds);
        return user;
    }

    @Override
    public List<UserDO> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Transactional
    @Override
    public int save(UserDO user) {
        int count = userMapper.save(user);
        if(count > 0){
            user.setUserId(userMapper.getMaxId()+1);
            userMapper.updateUserId(user);
        }
        Long userId = user.getUserId();
        List<Long> roles = user.getroleIds();
        userRoleMapper.removeByUserId(userId);

        List<UserRoleDO> list = buildUserRoleList(userId, roles);
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(UserDO user) {
        int r = userMapper.update(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getroleIds();
        userRoleMapper.removeByUserId(userId);

        List<UserRoleDO> list = buildUserRoleList(userId, roles);
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return r;
    }

    @Override
    public int remove(Long userId) {
        userRoleMapper.removeByUserId(userId);
        return userMapper.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(UserDO user) {
        int r = userMapper.update(user);
        return r;
    }

    @Transactional
    @Override
    public int batchremove(Long[] userIds) {
        int count = userMapper.batchRemove(userIds);
        userRoleMapper.batchRemoveByUserId(userIds);
        return count;
    }

    private List<UserRoleDO> buildUserRoleList(Long userId, List<Long> roles) {
        List<UserRoleDO> list = new ArrayList<UserRoleDO>();
        for (Long roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return list;
    }

    @Autowired
    UserDao userMapper;

    @Autowired
    UserRoleDao userRoleMapper;

    @Autowired
    DeptDao sysDeptMapper;
}
