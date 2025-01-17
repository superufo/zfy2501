package com.huadea.ext.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huadea.ext.domain.User;
import com.huadea.ext.mapper.UserMapper;
import com.huadea.ext.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}",username);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("police_number", username).last("limit 1");
        //查询某一个具体的用户
        User user = this.getOne(queryWrapper, false); //userMapper.getOne(queryWrapper, false);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在，登录失败");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        return user;
    }

}
