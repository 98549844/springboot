package com.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface HibernateBaseDao<T> {


    /**
     * 获取记录总数
     *
     * @param hql        HQL查询语句
     * @param parameters 参数
     * @return 记录总数
     */
    public int getTotal(String hql, Map<String, Object> parameters);

    /**
     * @param hql        HQL查询语句
     * @param parameters 参数
     * @return id
     */
    public int getId(String hql, Map<String, Object> parameters);

    /**
     * 查询总数
     *
     * @param hql
     * @param parameters
     * @return
     */
//	public double getAmount(String hql, Map<String, Object> parameters);

    /**
     * 统计一组数据
     *
     * @param hql
     * @param parameters
     * @return
     */
    public Double[] getAmounts(String hql, Map<String, Object> parameters);

    /**
     * 执行HQL语句
     *
     * @param hql
     * @param parameters
     */
    public int executeByHql(final String hql, final Map<String, Object> parameters);

}
