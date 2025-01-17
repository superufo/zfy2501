package com.huadea.ext.mapper;

import com.huadea.ext.domain.Auth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@Mapper
public interface AuthMapper extends BaseMapper<Auth> {
    @Select("SET IDENTITY_INSERT auth  ON")
    void  enableIdentityInsert();

    @Select(" SET IDENTITY_INSERT auth OFF")
    void  disableIdentityInsert();
}