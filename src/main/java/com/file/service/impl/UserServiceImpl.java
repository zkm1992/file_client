package com.file.service.impl;

import com.file.mapper.UserMapper;
import com.file.model.User;
import com.file.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户接口实现类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.getAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

}