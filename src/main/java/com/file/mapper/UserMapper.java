package com.file.mapper;

import com.file.model.User;

import java.util.List;

/**
 * 用户DAO接口
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public interface UserMapper {

    /**
     * 插入用户信息
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 通过主键查询
     *
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 查询全部用户
     *
     * @return
     */
    List<User> getAllUser();

}