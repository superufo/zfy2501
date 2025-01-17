package com.huadea.ext.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huadea.ext.domain.Auth;
import com.huadea.ext.domain.Role;
import com.huadea.ext.mapper.AuthMapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
public interface IAuthService extends IService<Auth> {
    public IPage<Auth> getByAuthId(int current, int size, Auth auth);


}
