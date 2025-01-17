package com.huadea.ext.domain.vo;

import com.huadea.ext.domain.Module;
import lombok.Data;

public class ModuleRoleVo  extends Module {
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    Integer  roleId;
}
