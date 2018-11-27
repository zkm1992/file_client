package com.file.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Data
public class User implements Serializable {

    /** 用户id, 主键. */
    private Integer userId;

    /** 用户名. */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /** 密码. */
    private String password;

    /** 年龄. */
    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "未成年人禁止入内")
    private Integer age;

    /** 罩杯. */
    private String cupSize;

    /** 手机号. */
    private String phone;

}