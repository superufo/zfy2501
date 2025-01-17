package com.huadea.ext.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huadea.ext.domain.Module;
import com.huadea.ext.domain.vo.ModuelsWithRoleVo;
import com.huadea.ext.domain.vo.ModuleRoleVo;
import com.huadea.ext.mapper.ModuleMapper;
import com.huadea.ext.service.IModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@Service
@Slf4j
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {
    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public List<ModuelsWithRoleVo> getAllModuelsWithRole() {
        // 按RoleId排序
        List<ModuleRoleVo>  list =  moduleMapper.getAllModuels();
        list =  list.stream()
                .sorted(Comparator.comparing(ModuleRoleVo::getRoleId)).toList();
        List<ModuelsWithRoleVo> res = new  ArrayList<>();
        List<Module> modules = new ArrayList<>();;

        for(int i=0;i<list.size();i++){
            if ( i+1 < list.size() &&  !list.get(i).getRoleId().equals(list.get(i+1).getRoleId()) ){
                ModuelsWithRoleVo  mrv = ModuelsWithRoleVo.builder().roleId(list.get(i).getRoleId()).children(modules).build();
                res.add(mrv);
                modules.clear();
            }
            modules.add(list.get(i));
        }

         return res;
    }

}
