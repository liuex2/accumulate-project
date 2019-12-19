package com.cif.accumulate.dao.util;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @Author: liuxincai
 * @Description: 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2等
 * @Date: 2019/12/19 16:28
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyInsertListMapper<T> {
    /**
     * 批量插入，支持批量插入的数据库可以使用 自定义主键ID
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true)
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertContainIdList(List<? extends T> recordList);
}
