package com.file.constants;

/**
 * 响应状态枚举类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public enum ResponseStatus {

    /** 成功. */
    SUCCESS(0, "成功"),
    /** 失败. */
    FAIL(1, "失败"),
    /** 系统异常. */
    SYSTEM_ERROR(2, "系统异常"),
    /** 数据保存异常. */
    DATA_SAVE_ERROR(3, "数据保存异常"),
    /** 未知错误. */
    UNKNOWN_ERROR(-1, "未知错误");

    /** 响应状态码. */
    private Integer statusCode;

    /** 响应状态说明. */
    private String statusName;

    ResponseStatus(Integer statusCode, String statusName) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public static void main(String[] args) {
        for (ResponseStatus responseStatus : ResponseStatus.values()) {
            System.out.println(responseStatus.getStatusCode() + "_" + responseStatus.getStatusName());
        }
    }

}