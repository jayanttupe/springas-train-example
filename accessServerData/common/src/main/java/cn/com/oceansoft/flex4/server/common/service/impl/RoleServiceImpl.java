package cn.com.oceansoft.flex4.server.common.service.impl;

import cn.com.oceansoft.flex4.server.common.dao.RoleDao;
import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service实现类 - 角色
 */

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {

    @Resource
    RoleDao roleDao;

    @Resource
    public void setBaseDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
        roleDao.flush();
    }

    @Override
    public void delete(String id) {
        Role role = roleDao.load(id);
        this.delete(role);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            Role role = roleDao.load(id);
            roleDao.delete(role);
        }
        roleDao.flush();
    }

    @Override
    public String save(Role role) {
        String id = roleDao.save(role);
        roleDao.flush();
        roleDao.clear();
        return id;
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
        roleDao.flush();
    }

}
