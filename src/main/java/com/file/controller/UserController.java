package com.file.controller;

import com.file.constants.ResponseStatus;
import com.file.model.User;
import com.file.service.UserService;
import com.file.utils.StatusMsg;
import com.file.utils.TransJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * user controller
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getById/{userId}")
    @Cacheable(key = "'userId_#userId'", value = "user")
    public User getById(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        logger.info(user.toString());
        return user;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public String addUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TransJsonUtil.Obj2Json("", new StatusMsg(ResponseStatus.FAIL, bindingResult.getFieldError().getDefaultMessage()));
        }
        String result;
        //返回insert的数量
        int i = userService.addUser(user);
        if (i > 0) {
            result = TransJsonUtil.Obj2Json(i, new StatusMsg(ResponseStatus.SUCCESS));
        } else {
            result = TransJsonUtil.Obj2Json("", new StatusMsg(ResponseStatus.FAIL, "添加用户失败"));
        }
        return result;
    }

    /**
     * 分页查询用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/all/{pageNum}/{pageSize}")
    public List<User> getAllUser(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<User> allUser = userService.getAllUser(pageNum, pageSize);
        return allUser;
    }

}