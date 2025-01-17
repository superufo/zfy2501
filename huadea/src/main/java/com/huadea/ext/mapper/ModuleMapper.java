package com.huadea.ext.mapper;

import com.huadea.ext.domain.Module;
import com.huadea.ext.domain.vo.ModuleRoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@Mapper
public interface ModuleMapper extends BaseMapper<Module> {
    List<ModuleRoleVo> getAllModuels();
}