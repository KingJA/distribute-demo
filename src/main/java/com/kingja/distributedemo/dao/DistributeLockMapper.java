package com.kingja.distributedemo.dao;

import com.kingja.distributedemo.model.DistributeLock;
import com.kingja.distributedemo.model.DistributeLockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DistributeLockMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    long countByExample(DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int deleteByExample(DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int insert(DistributeLock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int insertSelective(DistributeLock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    List<DistributeLock> selectByExample(DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    DistributeLock selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int updateByExampleSelective(@Param("record") DistributeLock record, @Param("example") DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int updateByExample(@Param("record") DistributeLock record, @Param("example") DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int updateByPrimaryKeySelective(DistributeLock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Fri Sep 18 14:30:57 CST 2020
     */
    int updateByPrimaryKey(DistributeLock record);

    DistributeLock selectDistributeLock(@Param("businessCode")String businessCode);
}