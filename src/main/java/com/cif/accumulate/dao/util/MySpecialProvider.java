package com.cif.accumulate.dao.util;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * @Author: liuxincai
 * @Description: MySpecialProvider实现类，特殊方法实现类
 * @Date: 2019/12/19 16:27
 */
public class MySpecialProvider extends MapperTemplate {

    public MySpecialProvider(Class<?> mapperClass, MapperHelper mapperHelper) { super(mapperClass, mapperHelper); }

    /**
     * 批量插入
     *
     * @param ms
     */
    public String insertContainIdList(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append("<bind name=\"listNotEmptyCheck\" value=\"@tk.mybatis.mapper.util.OGNL@notEmptyCollectionCheck(list, '" + ms.getId() + " 方法参数为空')\"/>");
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass), "list[0]"));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnList) {
            if (column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }
        }
        sql.append("</trim>");
        sql.append("</foreach>");

        // 反射把MappedStatement中的设置主键名
        EntityHelper.setKeyProperties(EntityHelper.getPKColumns(entityClass), ms);

        return sql.toString();
    }

}
