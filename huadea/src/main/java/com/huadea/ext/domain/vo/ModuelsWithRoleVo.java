package com.huadea.ext.domain.vo;

import lombok.Builder;
import lombok.Data;
import com.huadea.ext.domain.Module;

import java.util.List;

@Data
@Builder
public class ModuelsWithRoleVo {
    List<Module> children ;

    Integer roleId;
}
