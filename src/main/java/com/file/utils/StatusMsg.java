package com.file.utils;

import com.file.constants.ResponseStatus;
import lombok.Data;

/**
 * 全局状态响应消息实体
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Data
public class StatusMsg {

    /** 状态码. */
    private int status;

    /** 返回消息. */
    private String msg;

    public StatusMsg() {
        this.status = ResponseStatus.SUCCESS.getStatusCode();
        this.msg = "";
    }

    public StatusMsg(ResponseStatus responseStatus) {
        this.status = responseStatus.getStatusCode();
        this.msg = responseStatus.getStatusName();
    }

    public StatusMsg(ResponseStatus responseStatus, String msg) {
        this.status = responseStatus.getStatusCode();
        this.msg = msg;
    }

}