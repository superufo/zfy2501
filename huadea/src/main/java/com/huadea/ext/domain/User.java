package com.huadea.ext.domain;

/**
 * <p>
 * 
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("`user`")
public class User  implements Serializable, UserDetails{
    private static final long serialVersionUID = 1L;

	private Integer id;

	@TableField(value="police_number")
	private String policeNumber;

	@TableField(value="full_name")
	private String fullName;

	private String passwd;

	@TableField(value="group_id")
	private Integer groupId;

	@TableField(value="role_id")
	private Integer roleId;

	@TableField(value="phone_code")
	private String phoneCode;

	private Integer sex;

	private String remark;

	@TableField(value="creator_id")
	private Integer creatorId;

	private String uid;

	private String sn;

	private Integer disk;

	//  角色id在use表中定义死为 id
	@TableField(exist = false)
	private List<Integer> roles;

	@TableField(exist = false)
	private String  username;
	/**
	 * 用户的角色/权限
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//this.roles.Stream().map(r->new SimpleGrantedAuthority(r.getName()))
		return roles.stream().map(r->new SimpleGrantedAuthority(r.toString())).collect(Collectors.toList());
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return this.policeNumber;
	}

	@Override
	public String getPassword() {
		return passwd;
	}

	public void setPassword(String password) {
		this.passwd = password;
	}
}
