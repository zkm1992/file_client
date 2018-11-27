package com.file.service;

import com.file.model.User;

import java.util.List;

/**
 * 用户接口
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public interface UserService {

    /**
     * 通过userId获取用户信息
     *
     * @param userId
     * @return
     */
    User getById(Integer userId);

    /**
     * 分页查询所有用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<User> getAllUser(int pageNum, int pageSize);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

}