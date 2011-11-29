package cn.com.oceansoft.flex4.server.common.dao.impl;

import cn.com.oceansoft.flex4.server.common.dao.ResourceDao;
import cn.com.oceansoft.flex4.server.common.entity.Resource;
import cn.com.oceansoft.flex4.server.common.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Dao实现类 - 资源
 */

@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource, String> implements ResourceDao {

    // 处理关联，忽略isSystem=true的对象
    @Override
    public void delete(Resource resource) {
        if (resource.getIsSystem()) {
            return;
        }
        Set<Role> roleSet = resource.getRoleSet();
        if (roleSet != null) {
            for (Role role : roleSet) {
                role.getResourceSet().remove(resource);
            }
        }
        super.delete(resource);
    }

    // 处理关联，忽略isSystem=true的对象
    @Override
    public void delete(String id) {
        Resource resource = load(id);
        this.delete(resource);
    }

    // 处理关联，忽略isSystem=true的对象。
    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            this.delete(id);
        }
    }

    // 设置isSystem=false。
    @Override
    public String save(Resource resource) {
        resource.setIsSystem(false);
        return super.save(resource);
    }

    // 忽略isSystem=true的对象。
    @Override
    public void update(Resource resource) {
        if (resource.getIsSystem()) {
            return;
        }
        super.update(resource);
    }

}