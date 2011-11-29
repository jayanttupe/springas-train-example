package cn.com.oceansoft.flex4.server.common.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * 实体类 - 角色
 */

@Entity
@Table(name="tbl_comm_role")
public class Role extends BaseEntity{

    private static final long serialVersionUID = -6718238800112233445L;

	private String value;// 角色标识
    private String displayName;// 角色名称
    private Boolean isSystem;// 是否为系统内置角色
	private String description;// 描述
    private String ouId;   //组织ID
    private String projectId;//项目ID

	private Set<User> userSet;// 管理员
	private Set<Resource> resourceSet;// 资源

    @Column(nullable = false)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

//	@Column(nullable = false, updatable = false)
	@Column(nullable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

	@ManyToMany(fetch = FetchType.EAGER)
    @OrderBy("displayName asc")
	public Set<Resource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

    @Column(length = 32)
    public String getOuId() {
        return ouId;
    }

    public void setOuId(String ouId) {
        this.ouId = ouId;
    }

    @Column(nullable = false, length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
