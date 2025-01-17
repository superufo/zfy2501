package com.huadea.ext.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huadea.ext.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
public interface IUserService extends IService<User>, UserDetailsService {
}
