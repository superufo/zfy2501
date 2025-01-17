package com.huadea.ext.service;

import com.huadea.ext.domain.Module;
import com.huadea.ext.domain.vo.ModuelsWithRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
public interface IModuleService extends IService<Module> {
    //  每个role id 对应的Moduels模块列表
    List<ModuelsWithRoleVo> getAllModuelsWithRole();

    //List<ModuelsWithRole> getAllModuelsWithRole();
}
