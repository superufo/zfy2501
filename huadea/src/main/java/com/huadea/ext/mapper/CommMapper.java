package com.huadea.ext.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

public interface CommMapper<T> extends BaseMapper<T> {
    @Select("SET IDENTITY_INSERT YourTable ON")
    void  enableIdentityInsert();

    @Select(" SET IDENTITY_INSERT YourTable OFF")
    void  disableIdentityInsert();
}
