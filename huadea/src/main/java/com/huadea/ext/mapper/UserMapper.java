package com.huadea.ext.mapper;

import com.huadea.ext.domain.User;
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
public interface UserMapper extends BaseMapper<User> {
      List<Integer> getUserRolesById(Integer userId);
}