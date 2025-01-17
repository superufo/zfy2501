package com.huadea.ext.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huadea.ext.domain.Auth;
import com.huadea.ext.mapper.AuthMapper;
import com.huadea.ext.service.IAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {
    @Autowired
    private AuthMapper authMapper;

    public IPage<Auth> getByAuthId(int current, int size,Auth auth) {
        IPage<Auth> page = new Page<>(current,size);
        QueryWrapper<Auth> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Auth::getRoleId,auth.getRoleId());
        return  authMapper.selectPage(page,queryWrapper);
    }

}
