package com.huadea.ext.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@Component
@Data
public class Auth implements Serializable {
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId
	private Integer id;

	/**
	 * 
	 */
	@TableField(value="role_id")
	private Integer roleId;

	/**
	 * 
	 */
	@TableField(value="module_id")
	private Integer moduleId;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

}
